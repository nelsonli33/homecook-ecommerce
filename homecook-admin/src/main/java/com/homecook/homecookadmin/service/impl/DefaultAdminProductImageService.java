package com.homecook.homecookadmin.service.impl;

import com.criteo.vips.VipsContext;
import com.criteo.vips.VipsException;
import com.criteo.vips.VipsImage;
import com.criteo.vips.enums.VipsImageFormat;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.StorageException;
import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.service.AdminProductImageService;
import com.homecook.homecookadmin.util.FileUtil;
import com.homecook.homecookcommon.dto.FileInfo;
import com.homecook.homecookcommon.service.GCSStorageService;
import com.homecook.homecookcommon.service.impl.UUIDKeyGenerator;
import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductImageEntity;
import com.homecook.homecookentity.repository.ProductImageRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "adminProductImageService")
public class DefaultAdminProductImageService extends AbstractBaseService implements AdminProductImageService
{
    private static final Logger log = LoggerFactory.getLogger(DefaultAdminProductImageService.class);

    private GCSStorageService gcsStorageService;
    private UUIDKeyGenerator uuidKeyGenerator;
    private ProductImageRepository productImageRepository;

    @Autowired
    public DefaultAdminProductImageService(
            @Qualifier(value = "gcsStorageService") GCSStorageService gcsStorageService,
            @Qualifier(value = "uuidKeyGenerator") UUIDKeyGenerator uuidKeyGenerator,
            ProductImageRepository productImageRepository
    )
    {
        this.gcsStorageService = gcsStorageService;
        this.uuidKeyGenerator = uuidKeyGenerator;
        this.productImageRepository = productImageRepository;
    }

    public ProductImageEntity getProductImageForId(Long productImageId)
    {
        final Optional<ProductImageEntity> productImage = productImageRepository.findById(productImageId);
        if (!productImage.isPresent())
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Product Images with id: " + productImageId + " not found.");
        }
        return productImage.get();
    }

    @Override
    public ProductImageEntity uploadProductImageForUrl(String imageUrl)
    {
        final MultipartFile multipartFile = downloadFileForUrl(imageUrl);
        if (multipartFile == null)
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.PRODUCT_IMAGE_UPLOAD_ERROR, "The given url: " + imageUrl + "is wrong. Please check again. It must provide full url including protocol.");
        }
        return uploadProductImageForMultipartFile(multipartFile);
    }

    protected MultipartFile downloadFileForUrl(String imageUrl)
    {
        String originfilename = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        String contentType = "";
        byte[] content;

        try
        {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int code = connection.getResponseCode();
            contentType = connection.getContentType();


            if (code == 200)
            {
                try (InputStream in = connection.getInputStream();
                     BufferedInputStream bis = new BufferedInputStream(in);
                     ByteArrayOutputStream bos = new ByteArrayOutputStream())
                {
                    content = new byte[1024];
                    int len;
                    while ((len = bis.read(content)) != -1)
                    {
                        bos.write(content, 0, len);
                    }
                    bos.flush();
                    return new MockMultipartFile(originfilename, originfilename, contentType, bos.toByteArray());
                }
            }
        }
        catch (IOException e)
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.PRODUCT_IMAGE_UPLOAD_ERROR, "The given url: " + imageUrl + "is wrong. Please check again. It must provide full url including protocol.");
        }
        return null;
    }


    public List<ProductImageEntity> getProductImagesForIds(List<Long> productImageIds)
    {
        final List<ProductImageEntity> productImageEntities = productImageRepository.findAllById(productImageIds);
        if (CollectionUtils.isEmpty(productImageEntities))
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Product Images with ids: " + StringUtils.join(productImageIds, ',') + " not found.");
        }
        return productImageEntities;
    }

    @Override
    public ProductImageEntity getProudctImageForProduct(ProductEntity productEntity, Long productImageId)
    {
        for (ProductImageEntity productImageEntity : productEntity.getImages())
        {
            if (productImageId.equals(productImageEntity.getId()))
            {
                return productImageEntity;
            }
        }
        throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Product Images with id: " + productImageId + " not found.");
    }


    public ProductImageEntity uploadProductImageForMultipartFile(MultipartFile file)
    {
        final List<Blob> blobs = uploadProductImageToStorage(file);

        sortBlobsByImageWidthDESC(blobs);

        // if the number of uploaded images is not equal 4, then we will use cron job to remove the images not assigned to specific product.
        ProductImageEntity productImageEntity = new ProductImageEntity();
        if (blobs.size() == 4)
        {
            productImageEntity.setFilename(blobs.get(0).getName());
            productImageEntity.setOriginfilename(file.getOriginalFilename());
            productImageEntity.setDetail(blobs.get(1).getName());
            productImageEntity.setNormal(blobs.get(2).getName());
            productImageEntity.setThumbnail(blobs.get(3).getName());

            getModelService().save(productImageEntity);
        }
        return productImageEntity;
    }

    @Override
    public void deleteProductImageForProduct(ProductEntity productEntity, Long productImageId)
    {
        validateParameterNotNullStandardMessage("productEntity", productEntity);
        validateParameterNotNullStandardMessage("productImageId", productImageId);

        final ProductImageEntity productImageEntity = getProudctImageForProduct(productEntity, productImageId);

        LinkedList<String> objectNames = new LinkedList<>();
        objectNames.add(productImageEntity.getFilename());
        objectNames.add(productImageEntity.getNormal());
        objectNames.add(productImageEntity.getDetail());
        objectNames.add(productImageEntity.getThumbnail());

        try
        {
            gcsStorageService.deleteObjects(objectNames);
            productImageEntity.setProduct(null);
            getModelService().remove(productImageEntity);
        }
        catch (StorageException e)
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.PROUDCT_IMAGE_DELETE_ERROR, "Product Image with id " + productImageId + " failed to delete.");
        }
    }


    protected List<Blob> uploadProductImageToStorage(MultipartFile file)
    {
        FileUtil.checkValidImageFile(file);

        List<Blob> result = new ArrayList<>();

        String filename = uuidKeyGenerator.generate().toString();
        String mimeType = file.getContentType();

        try
        {
            LinkedList<ImagePayload> imagePayloads = resizeImageForMultipartFile(file, filename);

            for (ImagePayload payload : imagePayloads)
            {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFilename(payload.getFilename());
                fileInfo.setBytes(payload.getBytes());
                fileInfo.setMimeType(mimeType);

                Map<String, String> metadata = new HashMap<>();
                metadata.put("width", String.valueOf(payload.getWidth()));
                metadata.put("height", String.valueOf(payload.getHeight()));
                fileInfo.setMetadata(metadata);

                final Blob blob = gcsStorageService.sendFileToStorage(fileInfo);
                result.add(blob);
            }

            imagePayloads.clear();
            return result;
        }
        catch (IOException e)
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.PRODUCT_IMAGE_UPLOAD_ERROR, "system cannot upload file.");
        }
    }

    public String getImagename(String filename, int width, int height)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(filename).append("_").append(width).append("x").append(height);
        return builder.toString();
    }

    private LinkedList<ImagePayload> resizeImageForMultipartFile(MultipartFile file, String filename) throws IOException
    {
        LinkedList<ImagePayload> resultList = new LinkedList<>();

        // convert origin image to imagePayload
        byte[] contents = file.getBytes();
        VipsImage image = new VipsImage(contents, contents.length);
        resultList.add(new ImagePayload(filename, file.getBytes(), image.getWidth(), image.getHeight()));

        ImagePayload result2 = resizeImageinternal(file, filename, 1000, 1000);
        resultList.add(result2);
        ImagePayload result3 = resizeImageinternal(file, filename, 450, 450);
        resultList.add(result3);
        ImagePayload result4 = resizeImageinternal(file, filename, 100, 100);
        resultList.add(result4);
        return resultList;
    }


    private ImagePayload resizeImageinternal(MultipartFile file, String filename, int width, int height)
    {
        // Set vips memory leak report at exit
        VipsContext.setLeak(true);
        try
        {
            byte[] contents = file.getBytes();
            VipsImage image = new VipsImage(contents, contents.length);

            int originImageWidth = image.getWidth();
            int originImageHeight = image.getHeight();

            image.thumbnailImage(width, height, false);
            int resizedImageWidth = image.getWidth();
            int resizedImageHeight = image.getHeight();
            log.info(filename + " has been correctly resized: ({},{}) -> ({},{})",
                    originImageWidth, originImageHeight, resizedImageWidth, resizedImageHeight);

            contents = image.writeToArray(getVipsImageFormatForMultipartFile(file), false);

            String newFilename = getImagename(filename, resizedImageWidth, resizedImageHeight);
            // Release object reference and resources
            image.release();

            return new ImagePayload(newFilename, contents, resizedImageWidth, resizedImageHeight);
        }
        catch (IOException | VipsException e)
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.IMAGE_RESIZE_ERROR, "Image resize error");
        }
    }


    private VipsImageFormat getVipsImageFormatForMultipartFile(MultipartFile file)
    {
        String mimeType = file.getContentType();
        switch (mimeType)
        {
            case "image/jpeg":
                return VipsImageFormat.JPG;
            case "image/png":
                return VipsImageFormat.PNG;
            case "image/webp":
                return VipsImageFormat.WEBP;
            default:
                throw new HomecookAdminRuntimeException(InternalErrorCode.IMAGE_FORMAT_NOT_SUPPORT, "image format only support jpg, png, webp.");
        }
    }

    private void sortBlobsByImageWidthDESC(List<Blob> blobs)
    {
        Collections.sort(blobs, (o1, o2) -> {
            final Map<String, String> o1Metadata = o1.getMetadata();
            final Map<String, String> o2Metadata = o2.getMetadata();

            int o1Width = Integer.parseInt(o1Metadata.get("width"));
            int o2Width = Integer.parseInt(o2Metadata.get("width"));

            return o2Width - o1Width;
        });
    }


    // inner class
    private class ImagePayload
    {
        private String filename;
        private byte[] bytes;
        private int width;
        private int height;

        public ImagePayload(String filename, byte[] bytes, int width, int height)
        {
            this.filename = filename;
            this.bytes = bytes;
            this.width = width;
            this.height = height;
        }

        public String getFilename()
        {
            return filename;
        }

        public void setFilename(String filename)
        {
            this.filename = filename;
        }

        public byte[] getBytes()
        {
            return bytes;
        }

        public void setBytes(byte[] bytes)
        {
            this.bytes = bytes;
        }

        public int getWidth()
        {
            return width;
        }

        public void setWidth(int width)
        {
            this.width = width;
        }

        public int getHeight()
        {
            return height;
        }

        public void setHeight(int height)
        {
            this.height = height;
        }
    }
}
