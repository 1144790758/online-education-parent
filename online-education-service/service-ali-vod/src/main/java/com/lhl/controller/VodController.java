package com.lhl.controller;

import com.lhl.commonUtils.CommonResult;
import com.lhl.service.VodUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @athor:lhl
 * @create:2020-07-17 12:16
 */
@RestController
@RequestMapping("/eduvod")
//@CrossOrigin
public class VodController {
    @Autowired
    VodUploadService vodUploadService;

    @PostMapping("/uploadVideoAli")
    public CommonResult uploadVideoAli(MultipartFile file){
        String id = vodUploadService.uploadStream(file);
        return CommonResult.ok().data("videoId",id);
    }

    @DeleteMapping("/deleteById/{id}")
    public CommonResult deleteById(@PathVariable String id){
        vodUploadService.deleteById(id);
        return CommonResult.ok();
    }

    //根据id获取视频凭证
    @GetMapping("/getPlayAuth/{id}")
    public CommonResult getPlayAuth(@PathVariable String id){
        String auth=vodUploadService.getPlayAuth(id);
        return CommonResult.ok().data("auth",auth);
    }
}
