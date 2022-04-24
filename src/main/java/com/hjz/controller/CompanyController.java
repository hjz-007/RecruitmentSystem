package com.hjz.controller;

import com.hjz.model.dto.CompanyRegisterDTO;
import com.hjz.model.po.ApiResult;
import com.hjz.model.po.Company;
import com.hjz.service.CompanyService;
import com.hjz.service.DeliveryService;
import com.hjz.service.RecruitmentInfoService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private final RecruitmentInfoService recruitmentInfoService;
    private final DeliveryService deliveryService;

    @Autowired
    public CompanyController(CompanyService companyService, RecruitmentInfoService recruitmentInfoService, DeliveryService deliveryService) {
        this.companyService = companyService;
        this.recruitmentInfoService = recruitmentInfoService;
        this.deliveryService = deliveryService;
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "成功！"),
            @ApiResponse(code = 130, message = "用户名已经存在"),
            @ApiResponse(code = 230, message = "邮箱已经存在"),
            @ApiResponse(code = 231, message = "邮箱验证码错误"),
            @ApiResponse(code = 232, message = "邮箱验证码无效")
    })
    @PostMapping("/register")
    public ApiResult<Void> register(@RequestBody @Valid CompanyRegisterDTO companyRegisterDTO){
        try {
            companyService.register(companyRegisterDTO);
        } catch (Exception e){
            return ApiResult.failed(230, "邮箱已存在");
        }
        return ApiResult.ok(null);
    }

    @PostMapping("/login")
    public ApiResult<Void> login(String username, String password){
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            // 执行登录方法，无异常即可
            subject.login(token);
        } catch (UnknownAccountException e){
            return ApiResult.failed(401,"用户名错误");
        } catch (IncorrectCredentialsException e) {
            return ApiResult.failed(401,"密码错误");
        }
        return ApiResult.ok(null);
    }

    @GetMapping("/detail/{email}")
    public ApiResult<Company> detail(@PathVariable("email") String email){
        return ApiResult.ok(companyService.queryCompanyByEmail(email));
    }

    @PutMapping("/update")
    public ApiResult<Boolean> update(@RequestBody Company company){
        return ApiResult.ok(companyService.updateById(company));
    }

    @RequestMapping("/noauthorized")
    @ResponseBody
    public ApiResult<Void> unauthorized(){
        return ApiResult.failed(401, "未经授权无法访问页面");
    }

}

