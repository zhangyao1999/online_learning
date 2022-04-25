package com.zy.eduservice.service;

import com.zy.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zy
 * @since 2022-02-15
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCouresId(String id);

    boolean deleteChapterById(String chapterId);

    void removeChapterCourseId(String courseId);
}
