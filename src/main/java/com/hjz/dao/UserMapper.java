package com.hjz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjz.model.po.Company;
import com.hjz.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description: 用户DAO
 **/
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    User getByName(String name);

    User getByEmail(String email);

    void delete(int userId);

    void save(User user);

    void update(User user);
}

