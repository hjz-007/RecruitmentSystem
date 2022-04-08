package com.hjz.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 投递状态
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("delivery")
public class Delivery {
    // 简历
    @TableId(value = "resume_id",type = IdType.AUTO)
    private int resumeId;

    // 用户id
    @TableField(value = "resume_id")
    private int userId;

    // 岗位id
    @TableField(value = "recruitment_id")
    private int recruitmentId;

    // 投递状态
    @TableField(value = "status")
    private String status;

    // 投递时间
    @TableField(value = "create_time")
    private Long createTime;
}
