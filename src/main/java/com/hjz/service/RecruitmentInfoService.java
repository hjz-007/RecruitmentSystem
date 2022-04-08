package com.hjz.service;

import com.hjz.model.dto.RecruitmentInfoAddDTO;
import com.hjz.model.po.RecruitmentInfo;
import com.hjz.model.query.RecruitmentInfoQuery;

import java.util.List;

public interface RecruitmentInfoService {
    void save(RecruitmentInfo recruitmentInfo);

    void deleteById(Integer recruitmentInfoId);

    void update(RecruitmentInfo recruitmentInfo);

    List<RecruitmentInfo> listByQuery(RecruitmentInfoQuery recruitmentInfoQuery);

    List<RecruitmentInfo> list();
}
