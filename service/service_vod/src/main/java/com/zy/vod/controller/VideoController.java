package com.zy.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.zy.commonutils.R;
import com.zy.servicebase.config.ExceptionHandler.MyException;
import com.zy.vod.service.VideoService;
import com.zy.vod.utils.ConstantVodUtils;
import com.zy.vod.utils.InitVodClient;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZY
 * @create 2022/4/25 15:06
 */
@RestController
@RequestMapping("/eduvod/video")
@Api("video控制层")
@CrossOrigin
public class VideoController {

    @Resource
    private VideoService videoService;

    //上传视频到阿里云
    @PostMapping("/uploadAlyVideo")
    public R uploadAlyVideo(MultipartFile file) {
        String videoId = this.videoService.uploadAlyVideo(file);
        return R.ok().data("videoId",videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("/removeAliyunVideo/{id}")
    public R removeAliyunVideo(@PathVariable String id) {
        try{
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e) {
            e.printStackTrace();
            throw new MyException(20001,"删除视频失败");
        }

    }

//    //删除多个阿里云视频得方法
//    @DeleteMapping("/delete-batch")
//    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
//        this.videoService.removeMoreAlyVideo(videoIdList);
//        return R.ok();
//    }

    //根据视频id获取视频凭证
    @GetMapping("/getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //想request里面设置视频id
            request.setVideoId(id);
            //得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        }catch (Exception e) {
            throw new MyException(20001,"获取凭证失败");
        }
    }
}

