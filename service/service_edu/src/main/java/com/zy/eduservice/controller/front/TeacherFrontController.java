package com.zy.eduservice.controller.front;

/**
 * @author ZY
 * @create 2022/4/30 8:31
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.commonutils.R;
import com.zy.eduservice.entity.EduCourse;
import com.zy.eduservice.entity.EduTeacher;
import com.zy.eduservice.service.EduCourseService;
import com.zy.eduservice.service.EduTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
@Slf4j
public class TeacherFrontController {

    @Resource
    private EduTeacherService teacherService;

    @Resource
    private EduCourseService courseService;

    //分页查询讲师的方法
    @PostMapping("/getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page,@PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        Map<String,Object> map = this.teacherService.getTeacherFrontList(pageTeacher);
        //返回分页里的所有数据
        return R.ok().data(map);
    }

    //讲师详情查询
    @GetMapping("/getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable String teacherId) {
        log.error("老师详情方法开始调用");
        //根据讲师id查询讲师基本信息
        EduTeacher eduTeacher = this.teacherService.getById(teacherId);
        //根据讲师id查询所有课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = this.courseService.list(wrapper);
        log.error("老师详情方法结束");
        return R.ok().data("teacher",eduTeacher).data("courseList",courseList);

    }
}

