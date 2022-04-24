package com.hjz.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPageByCompanyQuery implements Serializable {
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 企业id
     */
    private int companyId;
    /**
     * 职位id
     */
    private int recruitmentId;
}
