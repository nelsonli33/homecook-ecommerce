package com.homecook.homecookadmin.service;

import com.google.cloud.storage.Blob;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminProductImageService
{
    List<Blob> uploadProductImage(MultipartFile file);

    String getImagename(String filename, int width, int height);
}
