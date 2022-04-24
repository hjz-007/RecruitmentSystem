package com.hjz.model.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ResumeInfoQuery implements Serializable {
    /**
     * 页数
     */
    private int pageNum;
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 年龄
     */
    private Integer userAge;
    /**
     * 性别
     */
    private String userSex;
    /**
     * 学历
     */
    private String education;
    /**
     * 民族
     */
    private String nation;
}
