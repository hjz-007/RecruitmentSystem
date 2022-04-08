package com.hjz.recruitment_system;

import com.hjz.dao.CompanyMapper;
import com.hjz.model.dto.CompanyRegisterDTO;
import com.hjz.model.po.Company;
import com.hjz.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class RecruitmentSystemApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    CompanyMapper companyMapper;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        connection.close();
    }
    @Test
    void companyRegister(){
        List<Company> companies = companyMapper.selectList(null);
        System.out.println(companies);
    }

}
