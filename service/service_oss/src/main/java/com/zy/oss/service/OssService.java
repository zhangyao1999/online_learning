package com.zy.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZY
 * @create 2022/1/18 13:16
 */
public interface OssService {
    String upload(MultipartFile file);
}
