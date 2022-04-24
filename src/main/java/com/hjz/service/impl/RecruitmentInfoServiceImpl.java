package com.hjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.dao.RecruitmentInfoMapper;
import com.hjz.dao.ResumeMapper;
import com.hjz.model.po.RecruitmentInfo;
import com.hjz.model.po.Resume;
import com.hjz.model.query.RecruitmentInfoComQuery;
import com.hjz.model.query.RecruitmentInfoQuery;
import com.hjz.model.query.RecruitmentRecommendQuery;
import com.hjz.service.RecruitmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RecruitmentInfoServiceImpl implements RecruitmentInfoService {

    private final RecruitmentInfoMapper recruitmentInfoMapper;
    private final ResumeMapper resumeMapper;

    @Autowired
    public RecruitmentInfoServiceImpl(RecruitmentInfoMapper recruitmentInfoMapper, ResumeMapper resumeMapper) {
        this.recruitmentInfoMapper = recruitmentInfoMapper;
        this.resumeMapper = resumeMapper;
    }

    @Override
    public void save(RecruitmentInfo recruitmentInfo) {
        recruitmentInfo.setCreateTime(new Date());
        recruitmentInfo.setEnable(true);
        recruitmentInfoMapper.insert(recruitmentInfo);
    }

    @Override
    public void deleteById(Integer recruitmentInfoId) {
        recruitmentInfoMapper.deleteById(recruitmentInfoId);
    }

    @Override
    public void update(RecruitmentInfo recruitmentInfo) {
        if(recruitmentInfo.getRecruitmentId() == 0){
            recruitmentInfo.setCreateTime(new Date());
            recruitmentInfo.setEnable(true);
            recruitmentInfoMapper.insert(recruitmentInfo);
        } else{
            recruitmentInfoMapper.updateById(recruitmentInfo);
        }
    }

    @Override
    public RecruitmentInfo detail(Integer recruitmentId){
        return recruitmentInfoMapper.selectOne(new QueryWrapper<RecruitmentInfo>().lambda()
                .eq(RecruitmentInfo::getRecruitmentId, recruitmentId));
    }

    @Override
    public Page<RecruitmentInfo> pageByQuery(RecruitmentInfoQuery query) {
        QueryWrapper<RecruitmentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(RecruitmentInfo::isEnable, true)
                .ge(RecruitmentInfo::getSalary, query.getMinSalary())
                .le(RecruitmentInfo::getSalary, query.getMaxSalary())
                .orderByAsc(RecruitmentInfo::getCreateTime);
        if(!query.getType().equals("")){
            queryWrapper.lambda().like(RecruitmentInfo::getType, query.getType());
        }
        if(!query.getJobName().equals("")){
            queryWrapper.lambda().like(RecruitmentInfo::getJobName, query.getJobName());
        }
        if(!query.getCity().equals("")){
            queryWrapper.lambda().like(RecruitmentInfo::getAddress, query.getCity());
        }
        Page<RecruitmentInfo> page = new Page<>(query.getPageNum(), query.getPageSize());
        return recruitmentInfoMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<RecruitmentInfo> pageByCompanyId(RecruitmentInfoComQuery query) {
        QueryWrapper<RecruitmentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RecruitmentInfo::getCompanyId, query.getCompanyId());
        Page<RecruitmentInfo> page = new Page<>(query.getPageNum(), query.getPageSize());
        return recruitmentInfoMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<RecruitmentInfo> pageByResume(RecruitmentRecommendQuery query) {
        Resume resume = resumeMapper.selectOne(new QueryWrapper<Resume>().lambda().eq(Resume::getResumeId, query.getResumeId()));
        if (resume != null) {
            QueryWrapper<RecruitmentInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(RecruitmentInfo::getType, resume.getType());
            Page<RecruitmentInfo> page = new Page<>(query.getPageNum(), query.getPageSize());
            return recruitmentInfoMapper.selectPage(page, queryWrapper);
        } else {
            return null;
        }
    }

}
