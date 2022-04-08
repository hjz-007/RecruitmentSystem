package com.hjz.service;

import com.hjz.model.dto.CompanyRegisterDTO;
import com.hjz.model.dto.UserLoginDTO;
import com.hjz.model.dto.UserRegisterDTO;
import com.hjz.model.po.Company;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

public interface CompanyService {
    // 企业登录
    void login(UserLoginDTO loginDTO, HttpServletRequest request);

    // 企业注册
    void register(CompanyRegisterDTO registerDTO);

    // 获取邮箱验证码
    void getEmailCaptcha(String email);

    // 企业注销
    void logout(HttpServletRequest request);

    // 获取企业用户
    Company queryCompanyByEmail(String email);
}
