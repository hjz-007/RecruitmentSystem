package com.hjz.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 用户实体
 *
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    // 编号
    @TableId(value = "user_id", type = IdType.AUTO)
    private int userId;

    // 账号昵称
    @TableField(value = "user_name")
    private String userName;

    // 手机号码
    @TableField(value = "user_phone")
    private String userPhone;

    // 邮箱
    @TableField(value = "user_email")
    private String userEmail;

    // 密码
    @TableField(value = "user_password")
    private String userPassword;

    // 会话id
//    @TableField(value = "")
//    private String sessionId;
}
