package com.hjz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjz.model.po.ApiResult;
import com.hjz.model.po.Resume;
import com.hjz.model.query.ResumeInfoQuery;
import com.hjz.model.query.ResumeRecommendQuery;
import com.hjz.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    /**
     * 新增简历
     *
     * @param resume
     * @return
     */
    @PostMapping("/add")
    public ApiResult<Void> add(@RequestBody Resume resume){
        resumeService.add(resume);
        return ApiResult.ok(null);
    }

    /**
     * 修改简历-有则修改，无则创建
     *
     * @param resume
     * @return
     */
    @PutMapping("/update")
    public ApiResult<Void> update(@RequestBody Resume resume){
        resumeService.updateById(resume);
        return ApiResult.ok(null);
    }

    /**
     * 用户查询简历接口
     * @param userId
     * @return
     */
    @GetMapping("/detail/{userId}")
    public ApiResult<Resume> detailByUserId(@PathVariable("userId") int userId){
        return ApiResult.ok(resumeService.getOneByUserId(userId));
    }

    @GetMapping("/detailById/{resumeId}")
    public ApiResult<Resume> detailByResumeId(@PathVariable("resumeId") int resumeId){
        return ApiResult.ok(resumeService.detailById(resumeId));
    }

    /**
     * 人才市场查询接口
     * @param query
     * @return
     */
    @PostMapping("/page")
    public ApiResult<Page<Resume>> pageResume(@RequestBody ResumeInfoQuery query){
        return ApiResult.ok(resumeService.pageByQuery(query));
    }

    @PostMapping("/recommend")
    public ApiResult<Page<Resume>> recommendResume(@RequestBody ResumeRecommendQuery query){
        return ApiResult.ok(resumeService.recommendResumes(query));
    }
}
