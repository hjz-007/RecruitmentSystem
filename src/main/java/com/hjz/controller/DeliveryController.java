package com.hjz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.model.po.ApiResult;
import com.hjz.model.po.Delivery;
import com.hjz.model.query.DeliveryPageByCompanyQuery;
import com.hjz.model.query.DeliveryPageByRecruitmentQuery;
import com.hjz.model.query.DeliveryPageByUserQuery;
import com.hjz.model.vo.DeliveryComVo;
import com.hjz.model.vo.DeliveryUserVo;
import com.hjz.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    /**
     * 用户新增投递状态
     * @param delivery
     * @return
     */
    @PostMapping("/add")
    public ApiResult<Void> add(@RequestBody Delivery delivery){
        String resp = deliveryService.add(delivery);
        if(!resp.equals("")){
            return ApiResult.failed(232,resp);
        }
        return ApiResult.ok(null);
    }

    /**
     * 公司修改投递状态
     *
     * @param delivery
     * @return
     */
    @PutMapping("/update")
    public ApiResult<Void> update(@RequestBody Delivery delivery){
        deliveryService.updateById(delivery);
        return ApiResult.ok(null);
    }

    /**
     * 用户查询投递记录
     *
     * @param query
     * @return
     */
    @PostMapping("/userPage")
    public ApiResult<Page<DeliveryUserVo>> listByUser(@RequestBody DeliveryPageByUserQuery query){
        return ApiResult.ok(deliveryService.listByUserId(query));
    }

    /**
     * 企业根据岗位查询投递记录
     *
     * @param query
     * @return
     */
    @PostMapping("/recruitmentPage")
    public ApiResult<Page<DeliveryComVo>> listByRecruitment(@RequestBody DeliveryPageByRecruitmentQuery query){
        return ApiResult.ok(deliveryService.listByRecruitmentId(query));
    }

    /**
     * 根据企业id进行查询
     *
     * @param query
     * @return
     */
    @PostMapping("/companyPage")
    public ApiResult<Page<DeliveryComVo>> listByCompany(@RequestBody DeliveryPageByCompanyQuery query){
        return ApiResult.ok(deliveryService.listByCompanyId(query));
    }
}
