package com.hjz.service;

import com.hjz.model.po.Resume;

public interface ResumeService {
    void add(Resume resume);

    void deleteById(Integer resumeId);

    void updateById(Resume resume);

    Resume getOneByUserId(Integer userId);
}
