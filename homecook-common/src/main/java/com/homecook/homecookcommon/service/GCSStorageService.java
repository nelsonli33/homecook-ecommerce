package com.homecook.homecookcommon.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.StorageException;
import com.homecook.homecookcommon.dto.FileInfo;

import java.util.List;

public interface GCSStorageService
{
    Blob sendFileToStorage(FileInfo info);

    void deleteObjects(List<String> objectNames) throws StorageException;
}
