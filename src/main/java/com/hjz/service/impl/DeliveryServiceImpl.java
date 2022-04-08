package com.hjz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.dao.DeliveryMapper;
import com.hjz.model.po.Delivery;
import com.hjz.model.query.DeliveryPageByRecruitmentQuery;
import com.hjz.model.query.DeliveryPageByUserQuery;
import com.hjz.service.DeliveryService;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    DeliveryMapper deliveryMapper;
    @Override
    public void add(Delivery delivery) {
        deliveryMapper.insert(delivery);
    }

    @Override
    public void deleteById(Integer deliveryId) {
        deliveryMapper.deleteById(deliveryId);
    }

    @Override
    public void updateById(Delivery delivery) {
        deliveryMapper.updateById(delivery);
    }

    @Override
    public Page<Delivery> listByUserId(DeliveryPageByUserQuery query) {
        QueryWrapper<Delivery> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", query.getUserId());
        Page<Delivery> page = new Page<>(query.getPageNum(), query.getPageSize());
        return deliveryMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<Delivery> listByRecruitmentId(DeliveryPageByRecruitmentQuery query) {
        QueryWrapper<Delivery> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", query.getRecruitmentId());
        Page<Delivery> page = new Page<>(query.getPageNum(), query.getPageSize());
        return deliveryMapper.selectPage(page, queryWrapper);
    }
}
