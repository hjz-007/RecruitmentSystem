package com.hjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper,Company> implements CompanyService {
    private final CompanyMapper companyMapper;
    @Autowired
    public CompanyServiceImpl(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @Override
    public void login(UserLoginDTO loginDTO, HttpServletRequest request) {

    }

    @Override
    public void register(CompanyRegisterDTO registerDTO) throws Exception {
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Company::getCompanyEmail, registerDTO.getCompanyEmail());
        Company result = companyMapper.selectOne(queryWrapper);
        if(result != null) {
            throw new Exception("该邮箱已被注册");
        }
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
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Company::getCompanyEmail, email);
        return companyMapper.selectOne(queryWrapper);
    }
}
