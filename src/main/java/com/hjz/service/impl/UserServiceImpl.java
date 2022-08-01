package com.hjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjz.dao.ResumeMapper;
import com.hjz.dao.UserMapper;
import com.hjz.model.dto.UserLoginDTO;
import com.hjz.model.dto.UserRegisterDTO;
import com.hjz.model.po.Resume;
import com.hjz.model.po.User;
import com.hjz.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final ResumeMapper resumeMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper, ResumeMapper resumeMapper) {
        this.resumeMapper = resumeMapper;
        this.userMapper = userMapper;
    }

    @Override
    public String login(UserLoginDTO loginDTO, HttpServletRequest request) {
        return "ttt";
    }

    @Transactional(rollbackFor = Exception.class)
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
        createResume(user.getUserEmail());
    }

    @Override
    public void update(User user){
        userMapper.updateById(user);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.delete(id);
    }

    @Override
    public User getByEmail(String Email) {
        return userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserEmail, Email));
    }

    private void createResume(String Email){
        User user= getByEmail(Email);
        Resume resume = new Resume();
        resume.setUserId(user.getUserId());
        resume.setUserName("暂无");
        resume.setUserAge(0);
        resume.setUserSex("男");
        resume.setUserPhone("暂无");
        resume.setUserEmail("暂无");
        resumeMapper.insert(resume);
    }
}
