package com.zy.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ZY
 * @create 2022/4/25 15:06
 */
public interface VideoService {
    String uploadAlyVideo(MultipartFile file);

//    void removeMoreAlyVideo(List videoIdList);
}