package com.hjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.dao.CompanyMapper;
import com.hjz.dao.RecruitmentInfoMapper;
import com.hjz.dao.ResumeMapper;
import com.hjz.model.po.Company;
import com.hjz.model.po.RecruitmentInfo;
import com.hjz.model.po.Resume;
import com.hjz.model.query.RecruitmentInfoComQuery;
import com.hjz.model.query.RecruitmentInfoQuery;
import com.hjz.model.query.RecruitmentRecommendQuery;
import com.hjz.service.RecruitmentInfoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Service
public class RecruitmentInfoServiceImpl implements RecruitmentInfoService {
    private final CompanyMapper companyMapper;
    private final RecruitmentInfoMapper recruitmentInfoMapper;
    private final ResumeMapper resumeMapper;

    @Autowired
    public RecruitmentInfoServiceImpl(CompanyMapper companyMapper, RecruitmentInfoMapper recruitmentInfoMapper, ResumeMapper resumeMapper) {
        this.companyMapper = companyMapper;
        this.recruitmentInfoMapper = recruitmentInfoMapper;
        this.resumeMapper = resumeMapper;
    }
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(RecruitmentInfo recruitmentInfo) {
        Company company = companyMapper.selectById(recruitmentInfo.getCompanyId());
        if (company == null) {
            throw new Exception("查无此公司");
        }
        recruitmentInfo.setCompanyName(company.getCompanyName());
        recruitmentInfo.setCreateTime(new Date());
        recruitmentInfo.setEnable(true);
        recruitmentInfoMapper.insert(recruitmentInfo);
    }

    @Override
    public void deleteById(Integer recruitmentInfoId) {
        recruitmentInfoMapper.deleteById(recruitmentInfoId);
    }
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(RecruitmentInfo recruitmentInfo) {
        if(recruitmentInfo.getRecruitmentId() == 0){
            Company company = companyMapper.selectById(recruitmentInfo.getCompanyId());
            if (company == null) {
                throw new Exception("查无此公司");
            }
            recruitmentInfo.setCompanyName(company.getCompanyName());
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
                .orderByDesc(RecruitmentInfo::getCreateTime);
        if(Objects.nonNull(query.getMinSalary())){
            queryWrapper.lambda().ge(RecruitmentInfo::getSalary, query.getMinSalary());
        }
        if (Objects.nonNull(query.getMaxSalary())) {
            queryWrapper.lambda().le(RecruitmentInfo::getSalary, query.getMaxSalary());
        }
        if(Objects.nonNull(query.getType()) && !query.getType().equals("")){
            queryWrapper.lambda().like(RecruitmentInfo::getType, query.getType());
        }
        if(Objects.nonNull(query.getJobName()) && !query.getJobName().equals("")){
            queryWrapper.lambda().like(RecruitmentInfo::getJobName, query.getJobName());
        }
        if(Objects.nonNull(query.getCity()) && !query.getCity().equals("")){
            queryWrapper.lambda().like(RecruitmentInfo::getAddress, query.getCity());
        }
        Page<RecruitmentInfo> page = new Page<>(query.getPageNum(), query.getPageSize());
        return recruitmentInfoMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<RecruitmentInfo> pageByCompanyId(RecruitmentInfoComQuery query) {
        QueryWrapper<RecruitmentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RecruitmentInfo::getCompanyId, query.getCompanyId())
        .orderByDesc(RecruitmentInfo::getCreateTime);
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
            Page<RecruitmentInfo> page = new Page<>(query.getPageNum(), query.getPageSize());
           return recruitmentInfoMapper.selectPage(page, new QueryWrapper<RecruitmentInfo>());
        }
    }

}
