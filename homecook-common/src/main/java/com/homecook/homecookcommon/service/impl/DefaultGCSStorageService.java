package com.homecook.homecookcommon.service.impl;

import com.google.cloud.storage.*;
import com.homecook.homecookcommon.dto.FileInfo;
import com.homecook.homecookcommon.service.GCSStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
}
