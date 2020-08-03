package com.lhl.eduService.controller;


import com.lhl.eduService.domain.EduChapter;
import com.lhl.eduService.domain.vo.EduChapterVo;
import com.lhl.eduService.service.EduChapterService;
import com.lhl.commonUtils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lhl
 * @since 2020-07-05
 */
@Api(description="课程章节管理")
//@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {

    @Autowired
    EduChapterService eduChapterService;

    @GetMapping("/getChapterByCourseId/{courseId}")
    public CommonResult getChapterByCourseId(@PathVariable String courseId){
        List<EduChapterVo> chapterVideoList=eduChapterService.getChapterByCourseId(courseId);

        return CommonResult.ok().data("result",chapterVideoList);
    }

    @ApiOperation(value = "新增章节")
    @PostMapping("/addChapter")
    public CommonResult save(
            @ApiParam(name = "chapterVo", value = "章节对象", required = true)
            @RequestBody EduChapter chapter){
        eduChapterService.save(chapter);
        return CommonResult.ok();
    }

    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("/findChapterById/{id}")
    public CommonResult getById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){

        EduChapter chapter = eduChapterService.getById(id);
        return CommonResult.ok().data("item", chapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PutMapping("/updateChapter")
    public CommonResult updateById(
            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody EduChapter chapter){

        eduChapterService.updateById(chapter);
        return CommonResult.ok();
    }

    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("/removeChapterById/{chapterId}")
    public CommonResult removeById(
            @ApiParam(name = "chapterId", value = "章节ID", required = true)
            @PathVariable String chapterId){

        boolean result = eduChapterService.removeChapterById(chapterId);
        if(result){
            return CommonResult.ok();
        }else{
            return CommonResult.error().message("删除失败");
        }
    }
}

