package com.lhl.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @athor:lhl
 * @create:2020-07-04 1:25
 */
public interface OssService {
    String upLoadAvatar(MultipartFile file);
}
