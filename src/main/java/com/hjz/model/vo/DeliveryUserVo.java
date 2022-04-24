package com.hjz.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeliveryUserVo implements Serializable {
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
     * 岗位名
     */
    private String jobName;

    /**
     * 薪资
     */
    private int salary;

    /**
     * 工作地点
     */
    private String address;

    /**
     * 投递时间
     */
    private String education;

    /**
     * 岗位是否还招
     */
    private boolean isEnable;

    /**
     * 投递状态
     */
    private String status;

    /**
     * 投递时间
     */
    private String createTime;
}
