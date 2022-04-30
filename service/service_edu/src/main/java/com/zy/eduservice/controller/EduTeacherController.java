package com.zy.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.commonutils.R;
import com.zy.eduservice.entity.EduTeacher;
import com.zy.eduservice.entity.TeacherQuery;
import com.zy.eduservice.service.EduTeacherService;
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
@Api("讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
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


    @ApiOperation (value = "分页讲师列表")
    @GetMapping ("find/{page}/{limit}")
    public R pageQuery(
            @ApiParam (name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam (name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        eduTeacherService.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "分页讲师列表带条件")
    @PostMapping("find/{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody
            TeacherQuery teacherQuery){

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        eduTeacherService.pageQuery(pageParam, teacherQuery);//service 调用pagequery方法会把pageparam这个参数给放值 下边可以直接调用。
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        return save?R.ok():R.error();
    }

    @ApiOperation("查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher byId = eduTeacherService.getById(id);
        return R.ok().data("teacher",byId);
    }
    @ApiOperation("修改老师信息")
    @PostMapping ("update")
    public R updateById(
            @ApiParam (name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        eduTeacherService.updateById(teacher);
        return R.ok();
    }

}

