package com.hjz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.model.po.RecruitmentInfo;
import com.hjz.model.query.RecruitmentInfoComQuery;
import com.hjz.model.query.RecruitmentInfoQuery;
import com.hjz.model.query.RecruitmentRecommendQuery;

public interface RecruitmentInfoService {
    void save(RecruitmentInfo recruitmentInfo);

    void deleteById(Integer recruitmentInfoId);

    void update(RecruitmentInfo recruitmentInfo);

    RecruitmentInfo detail(Integer recruitmentId);

    Page<RecruitmentInfo> pageByQuery(RecruitmentInfoQuery recruitmentInfoQuery);

    Page<RecruitmentInfo> pageByCompanyId(RecruitmentInfoComQuery query);

    Page<RecruitmentInfo> pageByResume(RecruitmentRecommendQuery query);
}
