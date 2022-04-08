package com.hjz.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 公司实体
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("company")
public class Company {
    // 编号
    @TableId(value = "company_id", type = IdType.AUTO)
    private Integer companyId;

    // 企业名
    @TableField(value = "company_name")
    private String companyName;

    // 企业信息
    @TableField(value = "company_info")
    private String companyInfo;

    // 企业电话
    @TableField(value = "company_phone")
    private String companyPhone;

    // 邮箱
    @TableField(value = "company_email")
    private String companyEmail;

    // 密码
    @TableField(value = "company_password")
    private String companyPassword;

    // 会话id
//    @TableField(value = "sessionId")
//    private String sessionId;
}
