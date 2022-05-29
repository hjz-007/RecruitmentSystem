package com.hjz.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyVo implements Serializable {
    /**
     * 企业id
     */
    private Integer companyId;

}
