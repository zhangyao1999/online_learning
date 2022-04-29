package com.zy.edumsm.controller;

import com.zy.commonutils.R;
import com.zy.edumsm.utils.RandomUtil;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author ZY
 * @create 2022/4/27 15:09
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @GetMapping("/send/{emailaddress}")
    public R sendMessage(@PathVariable String emailaddress) {
        String s = redisTemplate.opsForValue().get(emailaddress);
        if(!StringUtils.isEmpty(s)){
            return  R.ok();
        }
        String code = RandomUtil.getFourBitRandom();
        try {
            HtmlEmail email = new HtmlEmail();  //不用更改
            email.setHostName("smtp.qq.com");  //需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.addTo(emailaddress);  // 收件地址
            email.setFrom("jczhangyao@qq.com", "张垚");//此处填邮箱地址和用户名,用户名可以任意填写
            email.setAuthentication("jczhangyao@qq.com", "qzqzxizhrnsfechc"); //此处填写邮箱地址和客户端授权码

            email.setSubject("在线学习网站");//此处填写邮件名，邮件名可任意填写
            email.setMsg("尊敬的用户您好,您本次的验证码是:" + code);//此处填写邮件内容

            email.send();
            redisTemplate.opsForValue().set(emailaddress,code,5, TimeUnit.MINUTES);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().message("发送失败");
        }

    }
}
