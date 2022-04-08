package com.hjz.model.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RecruitmentInfoQuery implements Serializable {

    // 方向
    private Integer directionId;

    // 类别
    private Integer typeId;

    // 最低薪资
    private Integer minSalary;

    // 页数
    private Integer pageNum;

    // 每页显示数量
    private Integer pageSize;



}
