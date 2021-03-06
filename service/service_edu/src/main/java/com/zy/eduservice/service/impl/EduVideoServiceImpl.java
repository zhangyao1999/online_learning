package com.zy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.commonutils.R;
import com.zy.commonutils.ResultCode;
import com.zy.eduservice.client.VodClient;
import com.zy.eduservice.entity.EduVideo;
import com.zy.eduservice.mapper.EduVideoMapper;
import com.zy.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.servicebase.config.ExceptionHandler.MyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author zy
 * @since 2022-02-15
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Resource
    private VodClient vodClient;

    @Override
    public void removeVideoCourseId(String courseId) {
        //根据课程id查出所有视频id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapperVideo);
        //List<EduVideo>变成List<String>
        List<String> list = new ArrayList<>();
        for (int i = 0; i < eduVideos.size(); i++) {
            String videoSourceId = eduVideos.get(i).getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                list.add(videoSourceId);
            }
        }
        if(list.size()>0){
            R r = vodClient.deleteBatch(list);
            if(r.getCode()== ResultCode.ERROR){
                throw new MyException(ResultCode.ERROR,"删除失败");
            }
        }

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

    @Override
    public void removeVideoChapterId(String chapter) {
        //根据课程id查出所有视频id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("chapter_id",chapter);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapperVideo);
        //List<EduVideo>变成List<String>
        List<String> list = new ArrayList<>();
        for (int i = 0; i < eduVideos.size(); i++) {
            String videoSourceId = eduVideos.get(i).getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                list.add(videoSourceId);
            }
        }
        if(list.size()>0){
            R r = vodClient.deleteBatch(list);
            if(r.getCode()== ResultCode.ERROR){
                throw new MyException(ResultCode.ERROR,"删除失败");
            }
        }

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapter);
        baseMapper.delete(wrapper);
    }
}
