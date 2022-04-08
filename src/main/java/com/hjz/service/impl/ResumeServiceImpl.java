package com.hjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjz.dao.ResumeMapper;
import com.hjz.model.po.Resume;
import com.hjz.service.ResumeService;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {
    ResumeMapper resumeMapper;
    @Override
    public void add(Resume resume) {
        resumeMapper.insert(resume);
    }

    @Override
    public void deleteById(Integer resumeId) {
        resumeMapper.deleteById(resumeId);
    }

    @Override
    public void updateById(Resume resume) {
        resumeMapper.updateById(resume);
    }

    @Override
    public Resume getOneByUserId(Integer userId) {
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        Resume resume = resumeMapper.selectOne(queryWrapper);
        return resume;
    }
}
