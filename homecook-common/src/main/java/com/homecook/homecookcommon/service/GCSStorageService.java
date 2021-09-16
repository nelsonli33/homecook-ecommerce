package com.homecook.homecookcommon.service;

import com.google.cloud.storage.Blob;
import com.homecook.homecookcommon.dto.FileInfo;

public interface GCSStorageService
{
    Blob sendFileToStorage(FileInfo info);
}
