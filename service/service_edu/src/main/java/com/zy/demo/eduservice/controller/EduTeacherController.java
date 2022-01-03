package com.zy.demo.eduservice.controller;


import com.zy.commonutils.R;
import com.zy.demo.eduservice.entity.EduTeacher;
import com.zy.demo.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zy
 * @since 2022-01-03
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findall")
    public R findAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        R r = R.ok().data("key", list);
        return r;
    }

    @ApiOperation(value = "删除老师更具id")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);

        return b?R.ok():R.error();
    }

}

