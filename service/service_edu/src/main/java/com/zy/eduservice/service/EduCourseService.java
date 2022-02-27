package com.zy.eduservice.service;

import com.zy.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.eduservice.entity.vo.CourseInfoVo;

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
}
