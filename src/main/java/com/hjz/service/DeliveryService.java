package com.hjz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.model.po.Delivery;
import com.hjz.model.query.DeliveryPageByCompanyQuery;
import com.hjz.model.query.DeliveryPageByRecruitmentQuery;
import com.hjz.model.query.DeliveryPageByUserQuery;
import com.hjz.model.vo.DeliveryComVo;
import com.hjz.model.vo.DeliveryUserVo;

public interface DeliveryService {
    String add(Delivery delivery);

    void deleteById(Integer deliveryId);

    void updateById(Delivery delivery);

    Page<DeliveryUserVo> listByUserId(DeliveryPageByUserQuery deliveryPageByUserQuery);

    Page<DeliveryComVo> listByRecruitmentId(DeliveryPageByRecruitmentQuery query);

    Page<DeliveryComVo> listByCompanyId(DeliveryPageByCompanyQuery query);
}
