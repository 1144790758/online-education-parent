package com.lhl.eduService.controller;


import com.lhl.eduService.domain.EduVideo;
import com.lhl.eduService.service.EduVideoService;
import com.lhl.commonUtils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author lhl
 * @since 2020-07-05
 */
@RestController
@RequestMapping("/eduservice/edu-video")
//@CrossOrigin
public class EduVideoController {


    @Autowired
    EduVideoService eduVideoService;

    @PostMapping("/addVideo")
    public CommonResult addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return CommonResult.ok();
    }

    // 需要将视频一并删除
    @DeleteMapping("/deleteVideo/{id}")
    public CommonResult deleteVideo(@PathVariable String id){
        eduVideoService.removeById(id);
        return CommonResult.ok();
    }

    // 需要将视频一并修改
    @PutMapping("/updateVideo")
    public CommonResult updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return CommonResult.ok();
    }



}

