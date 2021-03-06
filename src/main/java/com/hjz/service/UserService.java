package com.hjz.service;

import com.hjz.model.dto.UserLoginDTO;
import com.hjz.model.dto.UserRegisterDTO;
import com.hjz.model.po.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 用户服务接口
 **/

public interface UserService {
    // 用户登录
    String login(UserLoginDTO loginDTO, HttpServletRequest request);

    // 用户注册
    void register(UserRegisterDTO registerDTO) throws Exception;

    void update(User user);

    //删除用户
    void deleteUser(int id);

    User getByEmail(String Email);
}
