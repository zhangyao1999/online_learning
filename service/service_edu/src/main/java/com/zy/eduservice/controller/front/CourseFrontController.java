package com.zy.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.log.Log;
import com.zy.commonutils.JwtUtils;
import com.zy.commonutils.R;
import com.zy.eduservice.client.UcenterClient;
import com.zy.eduservice.entity.EduCourse;
import com.zy.eduservice.entity.chapter.ChapterVo;
import com.zy.eduservice.entity.frontvo.CourseFrontVo;
import com.zy.eduservice.entity.frontvo.CourseWebVo;
import com.zy.eduservice.service.EduChapterService;
import com.zy.eduservice.service.EduCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author ZY
 * @create 2022/4/30 9:35
 */
@RestController
@RequestMapping("/eduservice/coursefont")
@CrossOrigin
@Slf4j
public class CourseFrontController {

    @Resource
    private EduCourseService courseService;

    @Resource
    private EduChapterService chapterService;


//
//    @Resource
//    private OrdersClient ordersClient;

    //条件查询带分页
    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = this.courseService.getFrontCourseList(pageCourse,courseFrontVo);
        return R.ok().data(map);
    }

    //查询课程的详细信息
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id查询课程信息
        CourseWebVo courseWebVo = this.courseService.getBaseCourseInfo(courseId);
        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = this.chapterService.getChapterVideoByCouresId(courseId);
            return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);

    }

//    //根据课程id查询课程信息
//    @PostMapping("/getCourseInfoOrder/{id}")
//    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
//
//        CourseWebVo baseCourseInfo = this.courseService.getBaseCourseInfo(id);
//        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
//        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);
//        return courseWebVoOrder;
//    }
}

