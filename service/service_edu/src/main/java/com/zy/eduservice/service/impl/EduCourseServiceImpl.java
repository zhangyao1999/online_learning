package com.zy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.commonutils.ResultCode;
import com.zy.eduservice.entity.EduCourse;
import com.zy.eduservice.entity.EduCourseDescription;
import com.zy.eduservice.entity.vo.CourseInfoVo;
import com.zy.eduservice.entity.vo.CoursePublishVo;
import com.zy.eduservice.mapper.EduCourseMapper;
import com.zy.eduservice.service.EduChapterService;
import com.zy.eduservice.service.EduCourseDescriptionService;
import com.zy.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.eduservice.service.EduVideoService;
import com.zy.servicebase.config.ExceptionHandler.MyException;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

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

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        EduCourseDescription eduCourseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i==0){
            throw  new MyException(ResultCode.ERROR,"更新失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        boolean b = courseDescriptionService.updateById(eduCourseDescription);
        if (b == false) {
            throw  new MyException(ResultCode.ERROR,"更新失败");
        }

    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        //调用mapper
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
        this.eduVideoService.removeVideoCourseId(courseId);
        this.eduChapterService.removeChapterCourseId(courseId);
        this.courseDescriptionService.removeById(courseId);
        int res = baseMapper.deleteById(courseId);
        if(res == 0) {
            throw new MyException(ResultCode.ERROR,"删除失败");
        }
    }
//
//    @Override
//    public Map<String, Object> getFrontCourseList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {
//        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
//
//        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {
//            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
//        }
//        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
//            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
//        }
//        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) { //关注度
//            wrapper.orderByDesc("buy_count");
//        }
//        if(!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {
//            wrapper.orderByDesc("gmt_create");
//        }
//        if(!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
//            wrapper.orderByDesc("price");
//        }
//        baseMapper.selectPage(pageCourse,wrapper);
//        //把分页的数据获取出来放到map中
//        List<EduCourse> records = pageCourse.getRecords();
//        long current = pageCourse.getCurrent();
//        long pages = pageCourse.getPages();
//        long size = pageCourse.getSize();
//        long total = pageCourse.getTotal();
//        boolean hasNext = pageCourse.hasNext();
//        boolean hasPrevious = pageCourse.hasPrevious();
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("items", records);
//        map.put("current", current);
//        map.put("pages", pages);
//        map.put("size", size);
//        map.put("total", total);
//        map.put("hasNext", hasNext);
//        map.put("hasPrevious", hasPrevious);
//        return map;
//    }
//
//    @Override
//    public CourseWebVo getBaseCourseInfo(String courseId) {
//        return baseMapper.getBaseCourseInfo(courseId);
//    }
}
