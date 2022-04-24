package com.hjz.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description：简历
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("resume")
public class Resume {
    // 简历编号
    @TableId(value = "resume_id", type = IdType.AUTO)
    private int resumeId;

    // 用户编号
    @TableField(value = "user_id")
    private int userId;

    // 姓名
    @TableField(value = "user_name")
    private String userName;

    // 年龄
    @TableField(value = "user_age")
    private Integer userAge;

    // 性别
    @TableField(value = "user_sex")
    private String userSex;

    // 民族
    @TableField(value = "nation")
    private String nation;

    // 学历
    @TableField(value = "education")
    private String education;

    // 目标岗位类型
    @TableField(value = "type")
    private String type;

    // 教育经历
    @TableField(value = "edu_experience")
    private String eduExperience;

    // 手机号码
    @TableField(value = "user_phone")
    private String userPhone;

    // 邮箱
    @TableField(value = "user_email")
    private String userEmail;

    // 自我介绍
    @TableField(value = "introduction")
    private String introduction;

    // 实践经历
    @TableField(value = "experience")
    private String experience;

    // 获奖经历
    @TableField(value = "award")
    private String award;

    // 是否展示给企业
    @TableField(value = "enable")
    private boolean enable;
}
