package com.hjz.model.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RecruitmentRecommendQuery implements Serializable {
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 简历id
     */
    private int resumeId;
}
