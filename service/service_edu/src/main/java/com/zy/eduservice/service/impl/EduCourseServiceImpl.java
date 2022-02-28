package com.zy.eduservice.service.impl;

import com.zy.eduservice.entity.EduCourse;
import com.zy.eduservice.entity.EduCourseDescription;
import com.zy.eduservice.entity.vo.CourseInfoVo;
import com.zy.eduservice.mapper.EduCourseMapper;
import com.zy.eduservice.service.EduCourseDescriptionService;
import com.zy.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.servicebase.config.ExceptionHandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zy
 * @since 2022-02-15
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0){
            throw new MyException(20001,"添加课程失败");
        }
        String id = eduCourse.getId();//获取id
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(id);//放入id 需要实体类的主键设置修改为input
        boolean save = courseDescriptionService.save(eduCourseDescription);
        return id;//返回id 方便让前端调转页面
    }
}