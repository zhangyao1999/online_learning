package com.zy.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zy.oss.service.OssService;
import com.zy.oss.utils.ConstantPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.UUID;

/**
 * @author ZY
 * @create 2022/1/18 13:16
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Override
    public String upload(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName =ConstantPropertiesUtil.BUCKET_NAME;



        InputStream inputStream = null;
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            inputStream = file.getInputStream();
            String filePath = new DateTime().toString("yyyy/MM/dd");
            String filename= filePath+"/"+UUID.randomUUID().toString().replace("-","")+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());;
            ossClient.putObject(bucketName, filename, inputStream);
            String uploadUrl = "https://" + bucketName + "." + endpoint + "/" + filename;
            ossClient.shutdown();
            return uploadUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
