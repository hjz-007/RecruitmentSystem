package com.hjz.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @description: 注册请求对象
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "注册")
public class UserRegisterDTO {
    // 3-12位,大小写字母,数字及汉字组合
    @ApiModelProperty(value = "用户名", example = "hjz")
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{3,12}$")
    private String userName;

    @ApiModelProperty(required = true, value = "邮箱", example = "603773962@qq.com")
    @Email
    @NotNull
    private String userEmail;

    // 6-12位,大小写字母及数字组合
    @ApiModelProperty(required = true, value = "密码", example = "123456")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$")
    @NotNull
    private String userPassword;
}
