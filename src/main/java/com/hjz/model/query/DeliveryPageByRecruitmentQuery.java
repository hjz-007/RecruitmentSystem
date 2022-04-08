package com.hjz.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class DeliveryPageByRecruitmentQuery implements Serializable {
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 岗位id
     */
    private int recruitmentId;
}
