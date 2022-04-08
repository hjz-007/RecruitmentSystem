package com.hjz.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description 工作类型
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobType implements Serializable {

    private Integer id;

    private String name;
}
