package com.zy.educenter.service;

import com.zy.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zy
 * @since 2022-04-27
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

//    Integer countRegister(String day);
}
