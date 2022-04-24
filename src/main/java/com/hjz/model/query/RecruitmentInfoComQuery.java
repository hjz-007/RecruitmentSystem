package com.hjz.model.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class RecruitmentInfoComQuery implements Serializable {
    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 公司id
     */
    private Integer companyId;
}
