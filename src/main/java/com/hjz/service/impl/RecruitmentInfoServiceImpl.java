package com.hjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.dao.RecruitmentInfoMapper;
import com.hjz.model.dto.RecruitmentInfoAddDTO;
import com.hjz.model.po.RecruitmentInfo;
import com.hjz.model.query.RecruitmentInfoQuery;
import com.hjz.service.RecruitmentInfoService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentInfoServiceImpl implements RecruitmentInfoService {
    RecruitmentInfoMapper recruitmentInfoMapper;

    @Override
    public void save(RecruitmentInfo recruitmentInfo) {
        recruitmentInfoMapper.insert(recruitmentInfo);
    }

    @Override
    public void deleteById(Integer recruitmentInfoId) {
        recruitmentInfoMapper.deleteById(recruitmentInfoId);
    }

    @Override
    public void update(RecruitmentInfo recruitmentInfo) {
        recruitmentInfoMapper.updateById(recruitmentInfo);
    }

    @Override
    public List<RecruitmentInfo> listByQuery(RecruitmentInfoQuery query) {
        QueryWrapper<RecruitmentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("salary",query.getMinSalary())
        .eq("",query.getTypeId());
        Page<RecruitmentInfo> page = new Page<>(query.getPageNum(), query.getPageSize());
        IPage<RecruitmentInfo> recruitmentInfoIPage = recruitmentInfoMapper.selectPage(page,queryWrapper);
        return recruitmentInfoIPage.getRecords();
    }

    @Override
    public List<RecruitmentInfo> list() {
        return recruitmentInfoMapper.selectList(new QueryWrapper<RecruitmentInfo>());
    }
}
