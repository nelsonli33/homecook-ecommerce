package com.homecook.homecookadmin.service.impl;

import com.criteo.vips.VipsContext;
import com.criteo.vips.VipsException;
import com.criteo.vips.VipsImage;
import com.criteo.vips.enums.VipsImageFormat;
import com.google.cloud.storage.Blob;
import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.service.AdminProductImageService;
import com.homecook.homecookadmin.util.FileUtil;
import com.homecook.homecookcommon.dto.FileInfo;
import com.homecook.homecookcommon.service.GCSStorageService;
import com.homecook.homecookcommon.service.impl.GUIDKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service(value = "adminProductImageService")
public class DefaultAdminProductImageService extends AbstractBaseService implements AdminProductImageService
{

    private static final Logger log = LoggerFactory.getLogger(DefaultAdminProductImageService.class);

    private GCSStorageService gcsStorageService;
    private GUIDKeyGenerator guidKeyGenerator;

    @Autowired
    public DefaultAdminProductImageService(
            @Qualifier(value = "gcsStorageService") GCSStorageService gcsStorageService,
            @Qualifier(value = "guidKeyGenerator") GUIDKeyGenerator guidKeyGenerator
    )
    {
        this.gcsStorageService = gcsStorageService;
        this.guidKeyGenerator = guidKeyGenerator;
    }


    public List<Blob> uploadProductImage(MultipartFile file)
    {
        FileUtil.checkValidImageFile(file);

        List<Blob> result = new ArrayList<>();

        String filename = guidKeyGenerator.generate().toString().replace("-", "");
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

    public String getImagename(String filename, int width, int height) {
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
        ImagePayload result4 = resizeImageinternal(file, filename,100, 100);
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
            throw new HomecookAdminRuntimeException(InternalErrorCode.IMAGE_RESIZE_ERROR);
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
