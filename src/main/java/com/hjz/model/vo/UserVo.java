package com.hjz.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 简历id
     */
    private int resumeId;
}
