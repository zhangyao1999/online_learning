package com.zy.eduservice.controller;


import com.zy.commonutils.R;
import com.zy.eduservice.entity.eduSubject.OneSubject;
import com.zy.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zy
 * @since 2022-01-18
 */

@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getSubjectList(){
        List<OneSubject> list=eduSubjectService.getSubjectList();
        return  R.ok().data("list",list);
    }

}

