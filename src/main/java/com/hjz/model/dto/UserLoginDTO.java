package com.hjz.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 登录请求对象
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "登录请求对象")
public class UserLoginDTO {
    @ApiModelProperty(required = true, value = "用户名", example = "hjz")
    private String name;

    @ApiModelProperty(required = true, value = "密码", example = "123456")
    private String password;

    @ApiModelProperty(required = true, value = "验证码", example = "AC")
    private String captcha;
}
