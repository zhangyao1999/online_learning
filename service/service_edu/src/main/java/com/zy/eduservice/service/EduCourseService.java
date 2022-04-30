package com.zy.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.eduservice.entity.chapter.ChapterVo;
import com.zy.eduservice.entity.frontvo.CourseFrontVo;
import com.zy.eduservice.entity.frontvo.CourseWebVo;
import com.zy.eduservice.entity.vo.CourseInfoVo;
import com.zy.eduservice.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zy
 * @since 2022-02-15
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);
    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getFrontCourseList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

//    Map<String, Object> getFrontCourseList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);
//
    CourseWebVo getBaseCourseInfo(String courseId);
}
