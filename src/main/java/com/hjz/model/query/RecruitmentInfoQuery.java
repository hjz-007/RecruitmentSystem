package com.hjz.model.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RecruitmentInfoQuery implements Serializable {

    /**
     * 查找信息——公司名或者职位名
     */
    private String jobName;

    /**
     * 职位类型
     */
    private String type;

    /**
     * 最低薪资
     */
    private Integer minSalary;

    /**
     * 最高薪资
     */
    private Integer maxSalary;

    /**
     * 所在城市
     */
    private String  city;

    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 页大小
     */
    private Integer pageSize;



}
