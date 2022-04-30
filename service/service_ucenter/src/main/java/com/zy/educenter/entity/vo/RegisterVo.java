package com.zy.educenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

/**
 * @author ZY
 * @create 2022/4/29 15:59
 */
@Data
public class RegisterVo {

    @ApiModelProperty(value = "昵称")
    private String nickname;


    @ApiModelProperty(value = "邮箱")
    private String email;


    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;
}
