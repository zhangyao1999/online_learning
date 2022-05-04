package com.zy.educenter.controller;


import com.zy.commonutils.JwtUtils;
import com.zy.commonutils.MD5;
import com.zy.commonutils.R;
import com.zy.educenter.entity.UcenterMember;
import com.zy.educenter.entity.vo.RegisterVo;
import com.zy.educenter.service.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
@Slf4j
public class UcenterMemberController {

    @Resource
    private UcenterMemberService memberService;


    @PostMapping("update")
    public R updateUser(@RequestBody @Validated UcenterMember ucenterMember){
        log.error("密码是"+ucenterMember.getPassword());
        ucenterMember.setPassword(MD5.encrypt(ucenterMember.getPassword()));
        log.error("加密后密码"+ucenterMember.getPassword());
        memberService.updateById(ucenterMember);
        return R.ok().data("suc","修改成功");
    }

    //登陆
    @PostMapping("/login")
    public R loginUser(@RequestBody UcenterMember member) {
        String token = this.memberService.login(member);
        return R.ok().data("token",token);
    }

    //注册
    @PostMapping("/register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        this.memberService.register(registerVo);
        return R.ok();
    }

    //根据tocken获取用户信息
    @GetMapping("/getUserInfo")
    public R getUserInfo(HttpServletRequest request) {
        //调用jwt的工具类方法,根据request对象获取头信息,返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //根据id查询用户信息
        UcenterMember member = this.memberService.getById(memberId);
        log.error("获取的用户星系"+member.toString());
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
    //根据token字符串获取用户信息
    @PostMapping("getInfoUc/{id}")
    public Map<String,String> getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = memberService.getById(id);
        HashMap<String, String> res = new HashMap<>();
        res.put("nickname",ucenterMember.getNickname());
        res.put("avatar",ucenterMember.getAvatar());
        res.put("id",ucenterMember.getId());
        return res;
    }

//    //查询某一天的注册人数
//    @GetMapping("/countRegister/{day}")
//    public R countRegister(@PathVariable String day) {
//        Integer count = this.memberService.countRegister(day);
//        return R.ok().data("countRegister",count);
//    }
}


