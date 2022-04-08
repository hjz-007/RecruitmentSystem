package com.hjz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjz.model.dto.CompanyRegisterDTO;
import com.hjz.model.po.Company;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

    Company queryCompanyByEmail(String email);

    void register(CompanyRegisterDTO companyRegisterDTO);

    void update(Company company);
}
