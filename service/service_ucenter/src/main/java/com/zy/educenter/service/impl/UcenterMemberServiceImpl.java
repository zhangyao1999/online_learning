package com.zy.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.commonutils.JwtUtils;
import com.zy.commonutils.MD5;
import com.zy.educenter.entity.UcenterMember;
import com.zy.educenter.entity.vo.RegisterVo;
import com.zy.educenter.mapper.UcenterMemberMapper;
import com.zy.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.servicebase.config.ExceptionHandler.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author zy
 * @since 2022-04-27
 */
@Service
@Slf4j
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UcenterMember member) {
        //获取手机号和密码
        String email = member.getEmail();
        String password = member.getPassword();

        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new MyException(20001,"登录失败");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        UcenterMember emailMember = baseMapper.selectOne(wrapper);
        if(emailMember == null) {
            throw new MyException(20001,"邮箱号不正确");
        }

        //判断密码是否正确
        //因为存储到数据库的密码是加密的 所以把数据库的密码进行加密在比较
        if(!MD5.encrypt(password).equals(emailMember.getPassword())) {
            throw new MyException(20001,"密码错误");
        }

//        //判断用户是否呗禁用
//        if(emailMember.getIsDisabled()) {
//            throw new MyException(20001,"该用户已经呗禁用");
//        }

        //登录成功 使用jwt生成token字符串
        String token = JwtUtils.getJwtToken(emailMember.getId(), emailMember.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {

        //得到注册的数据
        String code = registerVo.getCode(); //验证码
        String email = registerVo.getEmail(); //邮箱
        String nickname = registerVo.getNickname(); //昵称
        String password = registerVo.getPassword(); //密码

        //非空判断
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)
                ||StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname) ) {
            throw new MyException(20001,"注册失败");
        }

        //判断验证码
        //获取redis的验证码

        log.error(code);
        log.error(email);
        String s = String.valueOf(redisTemplate.opsForValue().get(email));
        log.error(s);
        if(!code.equals(s)) {
            throw new MyException(20001,"验证不正确");
        }

        //判断邮箱是否存在,存在则不再添加
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0) {
            throw new MyException(20001,"邮箱已经被注册过");
        }

        //将数据添加到数据库
        UcenterMember user = new UcenterMember();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(MD5.encrypt(password));
        user.setIsDisabled(false);
        user.setAvatar("https://zy-edu-01.oss-cn-beijing.aliyuncs.com/01.jpg");
        System.out.println(user);
        baseMapper.insert(user);
    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }

//    @Override
//    public Integer countRegister(String day) {
//        return baseMapper.countRegister(day);
//    }
}

