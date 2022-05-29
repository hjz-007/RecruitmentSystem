package com.hjz.controller;

import com.hjz.model.dto.CompanyLoginDTO;
import com.hjz.model.dto.CompanyRegisterDTO;
import com.hjz.model.po.ApiResult;
import com.hjz.model.po.Company;
import com.hjz.model.vo.CompanyVo;
import com.hjz.service.CompanyService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

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
    public ApiResult<CompanyVo> login(@RequestBody @Valid CompanyLoginDTO loginDTO){
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(loginDTO.getCompanyEmail(), loginDTO.getCompanyPassword());
        CompanyVo companyVo = new CompanyVo();
        try {
            // 执行登录方法，无异常即可
            subject.login(token);
            Company company = companyService.queryCompanyByEmail(loginDTO.getCompanyEmail());
            companyVo.setCompanyId(company.getCompanyId());
        } catch (UnknownAccountException e){
            return ApiResult.failed(401,"用户名错误");
        } catch (IncorrectCredentialsException e) {
            return ApiResult.failed(401,"密码错误");
        }
        return ApiResult.ok(companyVo);
    }

    @GetMapping("/detail/{email}")
    public ApiResult<Company> detail(@PathVariable("email") String email){
        return ApiResult.ok(companyService.queryCompanyByEmail(email));
    }

    @PutMapping("/update")
    public ApiResult<Boolean> update(@RequestBody Company company){
        return ApiResult.ok(companyService.updateById(company));
    }

    @GetMapping("/detailById/{companyId}")
    public ApiResult<Company> detailById(@PathVariable("companyId") int companyId){
        return ApiResult.ok(companyService.queryCompanyById(companyId));
    }

}

