package com.homecook.homecookcommon.service.impl;

import com.google.cloud.BatchResult;
import com.google.cloud.storage.*;
import com.homecook.homecookcommon.dto.FileInfo;
import com.homecook.homecookcommon.service.GCSStorageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class DefaultGCSStorageService implements GCSStorageService
{
    private static final Logger log = LoggerFactory.getLogger(DefaultGCSStorageService.class);

    private String bucketName;
    private Storage storage;

    public DefaultGCSStorageService(String bucketName, Storage storage)
    {
        this.bucketName = bucketName;
        this.storage = storage;
    }

    @Override
    public Blob sendFileToStorage(FileInfo info)
    {
        BlobId blobId = BlobId.of(bucketName, info.getFilename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(info.getMimeType())
                .setMetadata(info.getMetadata())
                .build();

        Blob blob = storage.create(blobInfo, info.getBytes());
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        log.info("{} successfully upload to gcs.", info.getFilename());
        return blob;
    }

    @Override
    public void deleteObjects(List<String> objectNames) throws StorageException
    {
        final StorageBatch batch = storage.batch();

        List<BlobId> blobIds = new ArrayList<>();
        for(String objectName : objectNames) {
            if (StringUtils.isNotEmpty(objectName)) {
                BlobId blobId = BlobId.of(bucketName, objectName);
                if (storage.get(blobId).exists()) {
                    blobIds.add(blobId);
                }
            }
        }



        for(BlobId blobId : blobIds) {
            batch.delete(blobId).notify(new BatchResult.Callback<Boolean, StorageException>()
            {
                @Override
                public void success(Boolean result)
                {
                    // delete successfully
                    log.info("Object {}  was deleted from {}", blobId, bucketName);
                }

                @Override
                public void error(StorageException e)
                {
                    throw e;
                }
            });
        }

        batch.submit();
    }
}
