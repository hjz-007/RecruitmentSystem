package com.hjz.service.impl;

import com.hjz.dao.CompanyMapper;
import com.hjz.model.dto.CompanyRegisterDTO;
import com.hjz.model.dto.UserLoginDTO;
import com.hjz.model.po.Company;
import com.hjz.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyMapper companyMapper;

    @Override
    public void login(UserLoginDTO loginDTO, HttpServletRequest request) {

    }

    @Override
    public void register(CompanyRegisterDTO registerDTO) {
        Company company = new Company();
        BeanUtils.copyProperties(registerDTO, company);
        companyMapper.insert(company);
    }

    @Override
    public void getEmailCaptcha(String email) {

    }

    @Override
    public void logout(HttpServletRequest request) {

    }

    @Override
    public Company queryCompanyByEmail(String email) {
        return companyMapper.queryCompanyByEmail(email);
    }
}
