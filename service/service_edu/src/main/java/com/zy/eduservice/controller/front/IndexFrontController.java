package com.zy.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.commonutils.R;
import com.zy.eduservice.entity.EduCourse;
import com.zy.eduservice.entity.EduTeacher;
import com.zy.eduservice.service.EduCourseService;
import com.zy.eduservice.service.EduTeacherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author ZY
 * @create 2022/4/26 16:34
 */
@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Resource
    private EduCourseService courseService;

    @Resource
    private EduTeacherService teacherService;

    //查询8条热门课程,查询前4条名师
    @GetMapping("/index")
    //@Cacheable(key = "'index'",value = "edu")
    public R index() {
        //查询8条热门课程
//        QueryWrapper<EduCourse> courWrapper = new QueryWrapper<>();
//        courWrapper.orderByDesc("id");
//        courWrapper.last("limit 8");
        List<EduCourse> eduList = this.courseService.list(null);
        Set<EduCourse> eduset = new HashSet<EduCourse>();
        Random random = new Random();
        while (eduset.size()<=7){
            eduset.add(eduList.get(random.nextInt(eduList.size())));
        }
//        for (int i = 0 ;i<eduList.size();i++){
//            eduset.add(eduList.get(random.nextInt(eduList.size())));
//            if(eduset.size()>7){
//                break;
//            }
//        }
        //查询前4条名师
//        QueryWrapper<EduTeacher> teaWrapper = new QueryWrapper<>();
//        teaWrapper.orderByDesc("id");
//        teaWrapper.last("limit 4");
        List<EduTeacher> teacherList = this.teacherService.list(null);
        Set<EduTeacher> teacherset = new HashSet<EduTeacher>();
//        for (int i = 0 ;i<eduList.size();i++){
//            teacherset.add(teacherList.get(random.nextInt(teacherList.size())));
//            if(teacherset.size()>3){
//                break;
//            }
//        }
        while (teacherset.size()<=3){
            teacherset.add(teacherList.get(random.nextInt(teacherList.size())));
        }
        return R.ok().data("eduList",eduset).data("teacherList",teacherset);
    }
}
