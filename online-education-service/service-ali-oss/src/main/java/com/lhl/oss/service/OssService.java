package com.lhl.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @athor:lhl
 */
public interface OssService {
    String upLoadAvatar(MultipartFile file);
}
