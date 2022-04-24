package com.hjz.controller;


import com.hjz.model.dto.UserLoginDTO;
import com.hjz.model.dto.UserRegisterDTO;
import com.hjz.model.po.ApiResult;
import com.hjz.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService=userService;
    }
    //用户登录
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ApiResult<Void> login(@RequestBody UserLoginDTO loginDTO, HttpServletRequest request) {
        userService.login(loginDTO,request);
        return ApiResult.ok(null);
    }

    //用户注册
    @ApiOperation(value = "用户注册", notes = "用户名3-12位,由大小写字母数字及汉字组合;" +
            "密码6-12位,大小写字母及数字组合")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功！"),
            @ApiResponse(code = 130, message = "用户名已经存在"),
            @ApiResponse(code = 230, message = "邮箱已经存在"),
            @ApiResponse(code = 231, message = "邮箱验证码错误"),
            @ApiResponse(code = 232, message = "邮箱验证码无效")
    })
    @PostMapping("/register")
    public ApiResult<Void> register( @Valid @RequestBody UserRegisterDTO registerDTO) {
        try {
            userService.register(registerDTO);
        } catch (Exception e){
            return ApiResult.failed(230, "邮箱已存在");
        }
        return ApiResult.ok(null);
    }

    //注销
    @ApiOperation(value = "注销")
    @GetMapping("/logout")
    public ApiResult<String> logout(HttpServletRequest request) {
        userService.logout(request);
        return ApiResult.ok(null);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ApiResult<Void> deleteUser(@PathVariable("userId") int userId){
            System.out.println(userId);
            userService.deleteUser(userId);
            return ApiResult.ok(null);
    }
}
