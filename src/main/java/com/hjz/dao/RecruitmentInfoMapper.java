package com.hjz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjz.model.po.Company;
import com.hjz.model.po.RecruitmentInfo;
import com.hjz.model.query.RecruitmentInfoQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 招聘信息DAO
 **/
@Mapper
@Repository
public interface RecruitmentInfoMapper extends BaseMapper<RecruitmentInfo> {

//    void save(RecruitmentInfo recruitmentInfo);
//
//    void deleteById(Integer recruitmentInfoId);
//
//    void update(RecruitmentInfo recruitmentInfo);
//
//    List<RecruitmentInfo> listByQuery(RecruitmentInfoQuery recruitmentInfoQuery);

}