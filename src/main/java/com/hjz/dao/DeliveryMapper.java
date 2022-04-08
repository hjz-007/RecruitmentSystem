package com.hjz.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjz.model.po.Delivery;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DeliveryMapper extends BaseMapper<Delivery> {
}
