package com.hjz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.model.po.ApiResult;
import com.hjz.model.po.RecruitmentInfo;
import com.hjz.model.query.RecruitmentInfoComQuery;
import com.hjz.model.query.RecruitmentInfoQuery;
import com.hjz.model.query.RecruitmentRecommendQuery;
import com.hjz.service.RecruitmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruitmentInfo")
public class RecruitmentInfoController {

    private final RecruitmentInfoService recruitmentInfoService;

    @Autowired
    public RecruitmentInfoController(RecruitmentInfoService recruitmentInfoService) {
        this.recruitmentInfoService = recruitmentInfoService;
    }

    @PostMapping("/add")
    public ApiResult<Void> add(@RequestBody RecruitmentInfo recruitmentInfo) {
        recruitmentInfoService.save(recruitmentInfo);
        return ApiResult.ok(null);
    }

    @PostMapping("/update")
    public ApiResult<Void> update(@RequestBody RecruitmentInfo recruitmentInfo) {
        recruitmentInfoService.update(recruitmentInfo);
        return ApiResult.ok(null);
    }

    @DeleteMapping("/delete/{recruitmentInfoId}")
    public ApiResult<Void> deleteById(@PathVariable("recruitmentInfoId") Integer recruitmentInfoId) {
        recruitmentInfoService.deleteById(recruitmentInfoId);
        return ApiResult.ok(null);
    }

    @GetMapping("/detail/{recruitmentId}")
    public ApiResult<RecruitmentInfo> detail(@PathVariable("recruitmentId") Integer recruitmentId){
        return ApiResult.ok(recruitmentInfoService.detail(recruitmentId));
    }

    @PostMapping("/query")
    public ApiResult<Page<RecruitmentInfo>> getByQuery(@RequestBody RecruitmentInfoQuery query) {
        return ApiResult.ok(recruitmentInfoService.pageByQuery(query));
    }

    @PostMapping("/queryByCompanyId")
    public ApiResult<Page<RecruitmentInfo>> queryByCompanyId(@RequestBody RecruitmentInfoComQuery query){
        return ApiResult.ok(recruitmentInfoService.pageByCompanyId(query));
    }

    @PostMapping("/recommend")
    public ApiResult<Page<RecruitmentInfo>> recommend(@RequestBody RecruitmentRecommendQuery query){
        return ApiResult.ok(recruitmentInfoService.pageByResume(query));
    }
}
