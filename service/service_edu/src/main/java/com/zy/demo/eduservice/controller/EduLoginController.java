package com.zy.demo.eduservice.controller;

import com.zy.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZY
 * @create 2022/1/10 13:02
 */
@Api("登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
//配置跨域
public class EduLoginController {
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles", "[admin]").data("name", "admin")
                .data("avatar", "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1073244124,868127656&fm=26&gp=0.jpg");
    }
}
