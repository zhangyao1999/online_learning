package com.zy.eduservice.controller;


import com.zy.commonutils.R;
import com.zy.eduservice.client.VodClient;
import com.zy.eduservice.entity.EduVideo;
import com.zy.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author zy
 * @since 2022-02-15
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody @Validated EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    //刪除小节
    //删除小节同时把小节中的视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        System.out.println(id);
        //根据小节id查询出视频id，进行删除
        EduVideo eduVideobyId = eduVideoService.getById(id);
        String videoSourceId = eduVideobyId.getVideoSourceId();
        //判断是否有视频,有就删除
        if (!StringUtils.isEmpty(videoSourceId)) {
            //远程调用vod删除视频
            vodClient.removeAliyunVideo(videoSourceId);
        }
        //删除小节
        eduVideoService.removeById(id);
        return R.ok();
    }

    //根据小节id查询
    @GetMapping("/getVideoInfo/{id}")
    public R getVideoInfo(@PathVariable String id) {
        EduVideo eduVideo = this.eduVideoService.getById(id);
        return R.ok().data("eduVideo",eduVideo);
    }

    //修改小节
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        this.eduVideoService.updateById(eduVideo);
        return R.ok();
    }
}

