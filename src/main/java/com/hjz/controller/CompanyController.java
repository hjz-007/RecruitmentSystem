package com.hjz.controller;

import com.hjz.model.dto.CompanyRegisterDTO;
import com.hjz.service.CompanyService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/company")
public class CompanyController {
    CompanyService companyService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/company/login";
    }


    @RequestMapping("/index")
    public String toIndex(Model model){
        model.addAttribute("hello company");
        return "/company/index";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "/company/register";
    }

    @RequestMapping("/register")
    public String register(CompanyRegisterDTO companyRegisterDTO, Model model){
        try {
            companyService.register(companyRegisterDTO);
        } catch (Exception e){
            model.addAttribute("msg", "注册信息错误");
        }
        model.addAttribute("msg", "注册成功");
        return "/company/login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            // 执行登录方法，无异常即可
            subject.login(token);
        } catch (UnknownAccountException e){
            model.addAttribute("msg", "用户名错误");
            return "/company/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码错误");
            return "/company/login";
        }
        return "/company/index";
    }

    @RequestMapping("/noauthorized")
    @ResponseBody
    public String unauthorized(){
        return "未经授权无法访问页面";
    }
}

