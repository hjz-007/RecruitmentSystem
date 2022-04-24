package com.hjz.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeliveryComVo implements Serializable {
    /**
     * 投递id
     */
    private int deliveryId;

    /**
     * 简历id
     */
    private int resumeId;

    /**
     * 岗位id
     */
    private int recruitmentId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 投递时间
     */
    private String education;

    /**
     * 工作地点
     */
    private String address;

    /**
     * 岗位名
     */
    private String jobName;

    /**
     * 薪资
     */
    private int salary;

    /**
     * 岗位是否还招
     */
    private boolean enable;

    /**
     * 投递状态
     */
    private String status;

    /**
     * 投递时间
     */
    private String createTime;
}
