package com.zy.eduservice.controller;


import com.zy.commonutils.R;
import com.zy.commonutils.ResultCode;
import com.zy.commonutils.Verify;
import com.zy.eduservice.entity.EduChapter;
import com.zy.eduservice.entity.chapter.ChapterVo;
import com.zy.eduservice.service.EduChapterService;
import com.zy.eduservice.service.EduCourseService;
import com.zy.servicebase.config.ExceptionHandler.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

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
        return R.ok().data("allChapterVideo",list);
    }


    //添加课程章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody @Validated EduChapter eduChapter) {
        String title = eduChapter.getTitle();
        Integer sort = eduChapter.getSort();
        chapterService.save(eduChapter);

        return R.ok();
    }

    //查询根据id课程章节
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter chapterById = chapterService.getById(chapterId);
        return R.ok().data("chapter", chapterById);
    }


    //修改课程章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return R.ok();
    }

    //删除课程章节
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean result = chapterService.deleteChapterById(chapterId);
        if (result) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

