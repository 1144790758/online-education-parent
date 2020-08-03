package com.lhl.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhl.commonUtils.CustomException;
import com.lhl.eduService.domain.EduCourse;
import com.lhl.eduService.domain.vo.CourseQueryVo;
import com.lhl.eduService.service.EduCourseService;
import com.lhl.eduService.service.EduTeacherService;
import com.lhl.commonUtils.CommonResult;
import com.lhl.commonUtils.TeacherQuery;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.lhl.eduService.domain.EduTeacher;

import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lhl
 * @since 2020-06-24
 */
@RestController
//@CrossOrigin
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    EduTeacherService teacherService;

    @Autowired
    EduCourseService courseService;

    //用于前台查询讲师详情,包括其课程
    @GetMapping("/getTeacherDetail/{id}")
    public CommonResult getTeacherDetail(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        List<EduCourse>courses= courseService.getByTeacherId(id);
        return CommonResult.ok().data("teacher",teacher).data("courses",courses).data("course_count",courses.size());
    }

    @GetMapping("/all/{current}/{size}")
    public CommonResult findAll(@ApiParam("查询第current页") @PathVariable Integer current,
                                @ApiParam("查询size条记录") @PathVariable Integer size)
    {
        //分页查询,查询第current页的size条记录
        Page<EduTeacher> page=new Page<>(current,size);
        IPage<EduTeacher> list = teacherService.page(page, null);
        CommonResult result = CommonResult.ok().data("data", list).data("total",list.getTotal());
        return result;
    }

    @GetMapping("/findAllTeacher")
    public CommonResult findAllTeacher(){
        List<EduTeacher> teacherList= teacherService.findAllTeacher();
        return CommonResult.ok().data("teacherList",teacherList);
    }

    //逻辑删除讲师
    @DeleteMapping("/delete/{id}")
    public CommonResult removeById(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        return CommonResult.ok().success(flag);
    }

    @PostMapping(("condition_query/{current}/{size}"))
    public CommonResult condition_query(@ApiParam("查询第current页") @PathVariable Integer current,
                                        @ApiParam("查询size条记录") @PathVariable Integer size,
                                        @RequestBody(required = false) TeacherQuery teacherQuery) throws InterruptedException {
        System.out.println(teacherQuery);
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        Integer level = teacherQuery.getLevel();
        String name = teacherQuery.getName();

        QueryWrapper<EduTeacher> wrapper=new QueryWrapper();

        //注意wrapper中的方法要写数据库的列名
        if (!StringUtils.isEmpty(begin)){
            wrapper.gt("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.gt("gmt_modified",end);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //分页
        Page<EduTeacher> page=new Page<>(current,size);
        teacherService.page(page, wrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
//        Thread.sleep(2000);
        CommonResult result = CommonResult.ok().data("rows", records).data("total", total);
        return result;
    }




    @PostMapping("add_edu")
    public CommonResult add_edu(@RequestBody EduTeacher teacher){
        System.out.println(teacher);
        boolean b = teacherService.save(teacher);
        if (b){
            return CommonResult.ok();
        }
        return CommonResult.error();

    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public CommonResult getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        EduTeacher teacher = teacherService.getById(id);
        return CommonResult.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("{id}")
    public CommonResult updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        teacherService.updateById(teacher);
        return CommonResult.ok();
    }
}

