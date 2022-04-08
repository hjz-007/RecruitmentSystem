package com.hjz.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @decription:权限实体
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {

    private Integer id;

    // 权限值
    private String name;

    // 权限描述
    private String description;
}
