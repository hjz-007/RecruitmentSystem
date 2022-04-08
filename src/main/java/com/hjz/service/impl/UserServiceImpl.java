package com.hjz.service.impl;

import com.hjz.dao.UserMapper;
import com.hjz.model.dto.UserLoginDTO;
import com.hjz.model.dto.UserRegisterDTO;
import com.hjz.model.po.User;
import com.hjz.service.UserService;
import com.hjz.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.mappers.ModelMapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;


@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public void login(UserLoginDTO loginDTO, HttpServletRequest request) {
        User rightUser = userMapper.getByName(loginDTO.getName());
        String captchaAddress = "captcha:" +
                IpUtil.getAddress(request).replaceAll(":", "-");
    }

    @Override
    public void register(UserRegisterDTO registerDTO) {

    }

    @Override
    public void getEmailCaptcha(String email) {

    }

    @Override
    public void logout(HttpServletRequest request) {

    }

    @Override
    public void getCaptcha(String ip, ServletOutputStream outputStream) {

    }

    @Override
    public void deleteUser(int id) {
        userMapper.delete(id);
    }
}
