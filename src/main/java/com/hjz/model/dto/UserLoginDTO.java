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
    @ApiModelProperty(required = true, value = "用户邮箱", example = "1414@qq.com")
    private String userEmail;

    @ApiModelProperty(required = true, value = "密码", example = "123456")
    private String userPassword;

}
