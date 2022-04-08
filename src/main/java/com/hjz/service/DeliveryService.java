package com.hjz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.model.po.Delivery;
import com.hjz.model.query.DeliveryPageByRecruitmentQuery;
import com.hjz.model.query.DeliveryPageByUserQuery;

public interface DeliveryService {
    void add(Delivery delivery);

    void deleteById(Integer deliveryId);

    void updateById(Delivery delivery);

    Page<Delivery> listByUserId(DeliveryPageByUserQuery deliveryPageByUserQuery);

    Page<Delivery> listByRecruitmentId(DeliveryPageByRecruitmentQuery query);
}
