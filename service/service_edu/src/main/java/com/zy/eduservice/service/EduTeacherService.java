package com.zy.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.eduservice.entity.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zy
 * @since 2022-01-03
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

}
