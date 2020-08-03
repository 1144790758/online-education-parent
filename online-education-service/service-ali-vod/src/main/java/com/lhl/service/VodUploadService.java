package com.lhl.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @athor:lhl
 * @create:2020-07-17 12:37
 */
public interface VodUploadService {
    String uploadStream(MultipartFile file);

    void deleteById(String id);

    String getPlayAuth(String id);
}
