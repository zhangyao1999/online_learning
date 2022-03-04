package com.zy.eduservice.controller;


import com.zy.commonutils.R;
import com.zy.eduservice.entity.chapter.ChapterVo;
import com.zy.eduservice.service.EduChapterService;
import com.zy.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zy
 * @since 2022-02-15
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @GetMapping("/getChapterVideo/{id}")
    private R getChapterVideo(@PathVariable String id){
        List<ChapterVo> list =chapterService.getChapterVideoByCouresId(id);
        return R.ok().data("list",list);
    }

}

