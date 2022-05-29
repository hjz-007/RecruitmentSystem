package com.hjz.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 投递状态
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("delivery")
public class Delivery implements Serializable {
    // 投递id
    @TableId(value = "delivery_id",type = IdType.AUTO)
    private int deliveryId;

    // 简历id
    @TableField(value = "resume_id")
    private int resumeId;

    // 企业id
    @TableField(value = "company_id")
    private int companyId;

    // 岗位id
    @TableField(value = "recruitment_id")
    private int recruitmentId;

    // 投递状态
    @TableField(value = "status")
    private String status;

    // 投递时间
    @TableField(value = "create_time")
    private Date createTime;
}
