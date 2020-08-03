package com.lhl.eduService.controller;


import com.lhl.eduService.domain.vo.EduSubjectData;
import com.lhl.eduService.service.EduSubjectService;
import com.lhl.commonUtils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lhl
 * @since 2020-07-04
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
//@CrossOrigin
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;

    //根据前端传来的Excel文件,将课程分类添加到数据库,可以排除重复的一级分类添加
    @PostMapping("/add_subject")
    public CommonResult add_subject(MultipartFile file){
        eduSubjectService.addSubject(file);

        return CommonResult.ok();
    }

    //返回所有的一级标题,其中已经封装好了其二级子标题
    @GetMapping("/list_subject")
    public CommonResult list_subject(){
        List<EduSubjectData> result= eduSubjectService.listSubject();

        return CommonResult.ok().data("result",result);
    }

}

