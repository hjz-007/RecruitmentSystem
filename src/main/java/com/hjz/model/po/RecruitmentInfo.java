package com.hjz.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 招聘信息模型
 *
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("recruitment_info")
public class RecruitmentInfo {
    // 招聘编号
    @TableId(value = "recruitment_id", type = IdType.AUTO)
    private int recruitmentId;

    // 企业编号
    @TableField(value = "company_id")
    private Integer companyId;

    // 公司
    @TableField(value = "company_name")
    private String companyName;

    // 职位名称
    @TableField(value = "job_name")
    private String jobName;

    // 月薪
    @TableField(value = "salary")
    private String salary;

    // 职位要求
    @TableField(value = "requirement")
    private String requirement;

    // 工作地点
    @TableField(value = "address")
    private String address;

    // 方向
    @TableField(value = "direction")
    private String direction;

    // 类别
    @TableField(value = "type")
    private String type;

    // 职责
    @TableField(value = "responsibility")
    private String responsibility;

    // 创建时间
//    @TableField(value = "")
//    private Long createTime;
}
