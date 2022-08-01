package com.hjz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.model.po.Resume;
import com.hjz.model.query.ResumeInfoQuery;
import com.hjz.model.query.ResumeRecommendQuery;

public interface ResumeService {
    void add(Resume resume);

    void deleteById(Integer resumeId);

    void updateById(Resume resume);

    Resume detailById(Integer resumeId);

    Resume getOneByUserId(Integer userId);

    Page<Resume> pageByQuery(ResumeInfoQuery query);

    Page<Resume> recommendResumes(ResumeRecommendQuery query);
}
