package com.hjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.dao.DeliveryMapper;
import com.hjz.dao.RecruitmentInfoMapper;
import com.hjz.dao.ResumeMapper;
import com.hjz.model.po.Delivery;
import com.hjz.model.po.RecruitmentInfo;
import com.hjz.model.po.Resume;
import com.hjz.model.query.DeliveryPageByCompanyQuery;
import com.hjz.model.query.DeliveryPageByRecruitmentQuery;
import com.hjz.model.query.DeliveryPageByUserQuery;
import com.hjz.model.vo.DeliveryComVo;
import com.hjz.model.vo.DeliveryUserVo;
import com.hjz.service.DeliveryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryMapper deliveryMapper;
    private final ResumeMapper resumeMapper;
    private final RecruitmentInfoMapper recruitmentInfoMapper;

    private static final SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    public DeliveryServiceImpl(DeliveryMapper deliveryMapper, ResumeMapper resumeMapper, RecruitmentInfoMapper recruitmentInfoMapper) {
        this.deliveryMapper = deliveryMapper;
        this.resumeMapper = resumeMapper;
        this.recruitmentInfoMapper = recruitmentInfoMapper;
    }

    @Override
    public String add(Delivery delivery) {
        QueryWrapper<Delivery> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Delivery::getRecruitmentId, delivery.getRecruitmentId())
        .eq(Delivery::getResumeId, delivery.getResumeId());
        Delivery result = deliveryMapper.selectOne(queryWrapper);
        if(result != null){
            return "已经投递过该岗位";
        }
        delivery.setCreateTime(new Date());
        deliveryMapper.insert(delivery);
        return "";
    }

    @Override
    public void deleteById(Integer deliveryId) {
        deliveryMapper.deleteById(deliveryId);
    }

    @Override
    public void updateById(Delivery delivery) {
        QueryWrapper<Delivery> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Delivery::getDeliveryId, delivery.getDeliveryId());
        Delivery result = deliveryMapper.selectOne(queryWrapper);
        if( result == null ){
            delivery.setCreateTime(new Date());
            deliveryMapper.insert(delivery);
        }else {
            result.setStatus(delivery.getStatus());
            deliveryMapper.updateById(result);
        }
    }

    @Override
    public Page<DeliveryUserVo> listByUserId(DeliveryPageByUserQuery query) {
        Resume resume = resumeMapper.selectOne(new QueryWrapper<Resume>().lambda().eq(Resume::getUserId, query.getUserId()));
        if(resume == null) {
            return new Page<>(query.getPageNum(), query.getPageSize(), 0L);
        }
        QueryWrapper<Delivery> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Delivery::getResumeId, resume.getResumeId());
        Page<Delivery> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<Delivery> deliveryPage = deliveryMapper.selectPage(page, queryWrapper);
        List<Delivery> records = deliveryPage.getRecords();
        List<DeliveryUserVo> deliveryUserVos = new ArrayList<>();
        records.forEach(delivery ->{
            DeliveryUserVo deliveryUserVo = new DeliveryUserVo();
            RecruitmentInfo recruitmentInfo = recruitmentInfoMapper.selectOne(new QueryWrapper<RecruitmentInfo>().lambda()
                    .eq(RecruitmentInfo::getRecruitmentId, delivery.getRecruitmentId()));
            BeanUtils.copyProperties(delivery, deliveryUserVo);
            BeanUtils.copyProperties(recruitmentInfo,deliveryUserVo);
            deliveryUserVo.setCreateTime(sdf.format(delivery.getCreateTime()));
            deliveryUserVos.add(deliveryUserVo);
        });
        Page<DeliveryUserVo> result = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        result.setRecords(deliveryUserVos);
        return result;
    }

    @Override
    public Page<DeliveryComVo> listByRecruitmentId(DeliveryPageByRecruitmentQuery query) {
        QueryWrapper<Delivery> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Delivery::getRecruitmentId, query.getRecruitmentId());
        Page<Delivery> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<Delivery> deliveryPage = deliveryMapper.selectPage(page, queryWrapper);
        List<Delivery> records = deliveryPage.getRecords();
        List<DeliveryComVo> list = new ArrayList<>();
        records.forEach(delivery ->{
            DeliveryComVo vo = new DeliveryComVo();
            // 先查询简历简单信息
            Resume resume = resumeMapper.selectOne(new QueryWrapper<Resume>().lambda().eq(Resume::getResumeId, delivery.getResumeId()));
            // 再查询岗位信息
            RecruitmentInfo recruitmentInfo = recruitmentInfoMapper.selectOne(new QueryWrapper<RecruitmentInfo>().lambda()
                    .eq(RecruitmentInfo::getRecruitmentId, delivery.getRecruitmentId()));
            BeanUtils.copyProperties(delivery, vo);
            BeanUtils.copyProperties(recruitmentInfo, vo);
            BeanUtils.copyProperties(resume, vo);
            vo.setCreateTime(sdf.format(delivery.getCreateTime()));
            list.add(vo);
        });
        Page<DeliveryComVo> resp = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        resp.setRecords(list);
        return resp;
    }

    @Override
    public Page<DeliveryComVo> listByCompanyId(DeliveryPageByCompanyQuery query) {
        QueryWrapper<Delivery> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Delivery::getCompanyId, query.getCompanyId());
        if(query.getRecruitmentId() != 0){
            queryWrapper.lambda().eq(Delivery::getRecruitmentId, query.getRecruitmentId());
        }
        Page<Delivery> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<Delivery> deliveryPage = deliveryMapper.selectPage(page, queryWrapper);
        List<Delivery> records = deliveryPage.getRecords();
        Page<DeliveryComVo> resp = new Page<>(query.getPageNum(), query.getPageSize(),page.getTotal());
        List<DeliveryComVo> list = new ArrayList<>();
        records.forEach(delivery -> {
            DeliveryComVo vo = new DeliveryComVo();
            // 先查询简历简单信息
            Resume resume = resumeMapper.selectOne(new QueryWrapper<Resume>().lambda().eq(Resume::getResumeId, delivery.getResumeId()));
            // 再查询岗位信息
            RecruitmentInfo recruitmentInfo = recruitmentInfoMapper.selectOne(new QueryWrapper<RecruitmentInfo>().lambda()
                    .eq(RecruitmentInfo::getRecruitmentId, delivery.getRecruitmentId()));
            BeanUtils.copyProperties(delivery, vo);
            BeanUtils.copyProperties(recruitmentInfo, vo);
            BeanUtils.copyProperties(resume, vo);
            vo.setCreateTime(sdf.format(delivery.getCreateTime()));
            list.add(vo);
        });
        resp.setRecords(list);
        return resp;
    }
}
