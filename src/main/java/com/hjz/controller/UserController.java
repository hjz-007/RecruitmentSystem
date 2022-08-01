package com.hjz.controller;


import com.hjz.model.dto.UserLoginDTO;
import com.hjz.model.dto.UserRegisterDTO;
import com.hjz.model.po.ApiResult;
import com.hjz.model.po.Resume;
import com.hjz.model.po.User;
import com.hjz.model.vo.UserVo;
import com.hjz.service.ResumeService;
import com.hjz.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ResumeService resumeService;

    //用户登录
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ApiResult<UserVo> login(@RequestBody @Valid UserLoginDTO loginDTO, HttpServletRequest request) {
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getUserEmail(), loginDTO.getUserPassword());
        UserVo userVo = new UserVo();
        try {
            // 执行登录方法，无异常即可
            subject.login(token);
            User user = userService.getByEmail(loginDTO.getUserEmail());
            Resume resume = resumeService.getOneByUserId(user.getUserId());
            userVo.setUserId(user.getUserId());
            if(resume != null){
                userVo.setResumeId(resume.getResumeId());
            }
        } catch (UnknownAccountException e){
            return ApiResult.failed(401,"用户名错误");
        } catch (IncorrectCredentialsException e) {
            return ApiResult.failed(401,"密码错误");
        }
        return ApiResult.ok(userVo);
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

    @DeleteMapping("/deleteUser/{userId}")
    public ApiResult<Void> deleteUser(@PathVariable("userId") int userId){
            System.out.println(userId);
            userService.deleteUser(userId);
            return ApiResult.ok(null);
    }
}
