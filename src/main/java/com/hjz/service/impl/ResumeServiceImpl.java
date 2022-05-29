package com.hjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.dao.RecruitmentInfoMapper;
import com.hjz.dao.ResumeMapper;
import com.hjz.model.po.RecruitmentInfo;
import com.hjz.model.po.Resume;
import com.hjz.model.query.ResumeInfoQuery;
import com.hjz.model.query.ResumeRecommendQuery;
import com.hjz.service.ResumeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {
    private final ResumeMapper resumeMapper;
    private final RecruitmentInfoMapper recruitmentInfoMapper;

    public ResumeServiceImpl(ResumeMapper resumeMapper, RecruitmentInfoMapper recruitmentInfoMapper) {
        this.resumeMapper = resumeMapper;
        this.recruitmentInfoMapper = recruitmentInfoMapper;
    }

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
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Resume::getUserId, resume.getUserId());
        Resume result = resumeMapper.selectOne(queryWrapper);
        if(result == null){
            resumeMapper.insert(resume);
        }else{
            resumeMapper.updateById(resume);
        }
    }

    @Override
    public Resume getOneByUserId(Integer userId) {
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Resume::getUserId,userId);
        return resumeMapper.selectOne(queryWrapper);
    }
    @Override
    public Page<Resume> pageByQuery(ResumeInfoQuery query){
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Resume::isEnable, true);
        if(Objects.nonNull(query.getUserSex()) && !query.getUserSex().equals("")){
            queryWrapper.lambda().eq(Resume::getUserSex, query.getUserSex());
        }
        if(Objects.nonNull(query.getUserAge())){
            queryWrapper.lambda().eq(Resume::getUserAge, query.getUserAge());
        }
        if(Objects.nonNull(query.getNation()) && !query.getNation().equals("")){
            queryWrapper.lambda().eq(Resume::getNation, query.getNation());
        }
        if(Objects.nonNull(query.getEducation()) && !query.getEducation().equals("")){
            queryWrapper.lambda().eq(Resume::getEducation, query.getEducation());
        }
        Page<Resume> page = new Page<>(query.getPageNum(), query.getPageSize());
        return resumeMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<Resume> recommendResumes(ResumeRecommendQuery query) {
        List<RecruitmentInfo> recruitmentInfos = recruitmentInfoMapper.selectList(new QueryWrapper<RecruitmentInfo>()
                .lambda().eq(RecruitmentInfo::getCompanyId, query.getCompanyId()));
        List<String> collect = recruitmentInfos.stream().map(RecruitmentInfo::getType).collect(Collectors.toList());
        Page<Resume> page = new Page<>(query.getPageNum(), query.getPageSize());
        if(collect.isEmpty()){
           return new Page<>(query.getPageNum(), query.getPageNum());
        }
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(Resume::getType, collect).eq(Resume::isEnable, true);
        return resumeMapper.selectPage(page, queryWrapper);
    }

}
