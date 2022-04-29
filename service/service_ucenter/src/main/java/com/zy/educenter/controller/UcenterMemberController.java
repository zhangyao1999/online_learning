package com.zy.educenter.controller;


import com.zy.commonutils.JwtUtils;
import com.zy.commonutils.R;
import com.zy.educenter.entity.UcenterMember;
import com.zy.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author zy
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Resource
    private UcenterMemberService memberService;

    //登陆
    @PostMapping("/login")
    public R loginUser(@RequestBody UcenterMember member) {
        String token = this.memberService.login(member);
        return R.ok().data("token",token);
    }

//    //注册
//    @PostMapping("/register")
//    public R registerUser(@RequestBody RegisterVo registerVo) {
//        this.memberService.register(registerVo);
//        return R.ok();
//    }

    //根据tocken获取用户信息
    @GetMapping("/getUserInfo")
    public R getUserInfo(HttpServletRequest request) {
        //调用jwt的工具类方法,根据request对象获取头信息,返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //根据id查询用户信息
        UcenterMember member = this.memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }

//    //根据用户id获取用户信息
//    @PostMapping("/getUserInfoOrder/{id}")
//    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
//        UcenterMember member = this.memberService.getById(id);
//        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
//        BeanUtils.copyProperties(member,ucenterMemberOrder);
//        return ucenterMemberOrder;
//    }

//    //查询某一天的注册人数
//    @GetMapping("/countRegister/{day}")
//    public R countRegister(@PathVariable String day) {
//        Integer count = this.memberService.countRegister(day);
//        return R.ok().data("countRegister",count);
//    }
}


