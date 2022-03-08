package com.zy.eduservice.controller;


import com.zy.commonutils.R;
import com.zy.eduservice.entity.vo.CourseInfoVo;
import com.zy.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zy
 * @since 2022-02-15
 */
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
@Api("课程管理")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;
    @ApiOperation("添加课程信息")
    @PostMapping("addCourseInfo")
    public R addCourseInof(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }
    @ApiOperation("获取课程信息")
    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo=courseService.getCourseInfo(courseId);
        return R.ok().data("item",courseInfoVo);
    }
    @ApiOperation("更新课程信息")
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

}

