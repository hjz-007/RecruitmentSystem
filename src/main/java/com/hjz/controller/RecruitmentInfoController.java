package com.hjz.controller;

import com.hjz.model.dto.RecruitmentInfoAddDTO;
import com.hjz.model.po.ApiResult;
import com.hjz.model.po.RecruitmentInfo;
import com.hjz.model.query.RecruitmentInfoQuery;
import com.hjz.service.RecruitmentInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruitmentInfo")
public class RecruitmentInfoController {

    private RecruitmentInfoService recruitmentInfoService;

    @Autowired
    public RecruitmentInfoController(RecruitmentInfoService recruitmentInfoService) {
        this.recruitmentInfoService = recruitmentInfoService;
    }

    @PostMapping("/add")
    public String add(@RequestBody RecruitmentInfo recruitmentInfo, Model model) {
        recruitmentInfoService.save(recruitmentInfo);
        model.addAttribute("msg","插入成功");
        return "/company/index";
    }

    @PostMapping("/update")
    public String update(@RequestBody RecruitmentInfo recruitmentInfo, Model model) {
        recruitmentInfoService.update(recruitmentInfo);
        model.addAttribute("msg","修改成功");
        return "/company/index";
    }

    @GetMapping("/delete/{recruitmentInfoId}")
    public String deleteById(@PathVariable("recruitmentInfoId") Integer recruitmentInfoId, Model model) {
        recruitmentInfoService.deleteById(recruitmentInfoId);
        model.addAttribute("msg","删除成功");
        return "/company/index";
    }

    @GetMapping("/query")
    public ApiResult<List<RecruitmentInfo>> getByQuery(
            @RequestParam("directionId") Integer directionId, @RequestParam("typeId") Integer typeId,
            @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        RecruitmentInfoQuery recruitmentInfoQuery = new RecruitmentInfoQuery();
        ApiResult<List<RecruitmentInfo>> apiResult = new ApiResult<>();
        // 页数和每页显示数量必填
        if (pageNum == null || pageSize == null) {
            apiResult.setStatus(404);
            return apiResult;
        }
        recruitmentInfoQuery.setPageNum(pageNum);
        recruitmentInfoQuery.setPageSize(pageSize);
        if (directionId != null) {
            recruitmentInfoQuery.setDirectionId(directionId);
        }
        if(typeId != null){
            recruitmentInfoQuery.setTypeId(typeId);
        }
        List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoService.
                listByQuery(recruitmentInfoQuery);
        apiResult.setData(recruitmentInfoList);
        return apiResult;
    }
}
