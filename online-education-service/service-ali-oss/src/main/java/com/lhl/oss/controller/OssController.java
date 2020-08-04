package com.lhl.oss.controller;

import com.lhl.commonUtils.CommonResult;
import com.lhl.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @athor:lhl
 */
@RestController
@RequestMapping("/edu_oss")
//@CrossOrigin
public class OssController {

    @Autowired
    OssService ossService;

    @PostMapping("/upLoadAvatar")
    public CommonResult upLoadAvatar(MultipartFile file, HttpServletResponse response){//post发送一个文件流收集成file
        //上传到阿里云oss返回一个图片url
        String url=ossService.upLoadAvatar(file);

        return url==null?CommonResult.error().message("上传失败"):CommonResult.ok().data("url",url);
    }


}
