package com.zy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.eduservice.entity.EduChapter;
import com.zy.eduservice.entity.EduVideo;
import com.zy.eduservice.entity.chapter.ChapterVo;
import com.zy.eduservice.entity.chapter.VideoVo;
import com.zy.eduservice.mapper.EduChapterMapper;
import com.zy.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.eduservice.service.EduVideoService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zy
 * @since 2022-02-15
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCouresId(String id) {
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id",id);
        List<EduChapter> eduChapters = baseMapper.selectList(eduChapterQueryWrapper);


        //上面使查出章节
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",id);
        List<EduVideo> eduVideos = videoService.list(eduVideoQueryWrapper);
        //上面使查出所有小结
        ArrayList<ChapterVo> chapterVos = new ArrayList<>();
        for (int i = 0; i < eduChapters.size(); i++) {
            EduChapter eduChapter = eduChapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            ArrayList<VideoVo> videoVos = new ArrayList<>();
            for (int j = 0; j <eduVideos.size() ; j++) {
                if(eduVideos.get(j).getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideos.get(j),videoVo);
                    videoVos.add(videoVo);
                }
            chapterVo.setChildren(videoVos);
            }
            chapterVos.add(chapterVo);
        }

        return chapterVos;
    }
}
