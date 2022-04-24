package com.hjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjz.dao.UserMapper;
import com.hjz.model.dto.UserLoginDTO;
import com.hjz.model.dto.UserRegisterDTO;
import com.hjz.model.po.User;
import com.hjz.service.UserService;
import com.hjz.util.IpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class UserServiceImpl implements UserService {
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
    public void register(UserRegisterDTO registerDTO) throws Exception {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserEmail, registerDTO.getUserEmail());
        User result = userMapper.selectOne(queryWrapper);
        if(result != null) {
            throw new Exception("该邮箱已被注册");
        }
        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);
        userMapper.insert(user);
    }

    @Override
    public void update(User user){
        userMapper.updateById(user);
    }


    @Override
    public void logout(HttpServletRequest request) {

    }

    @Override
    public void deleteUser(int id) {
        userMapper.delete(id);
    }
}
