package com.hjz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjz.model.dto.CompanyRegisterDTO;
import com.hjz.model.dto.UserLoginDTO;
import com.hjz.model.po.Company;

import javax.servlet.http.HttpServletRequest;

public interface CompanyService extends IService<Company> {
    // 企业登录
    void login(UserLoginDTO loginDTO, HttpServletRequest request);

    // 企业注册
    void register(CompanyRegisterDTO registerDTO) throws Exception;

    // 根据邮箱获取企业
    Company queryCompanyByEmail(String email);

    // 根据id获取企业
    Company queryCompanyById(int Id);
}
