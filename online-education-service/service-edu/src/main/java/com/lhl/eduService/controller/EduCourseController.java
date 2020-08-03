package com.lhl.eduService.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.lhl.commonUtils.TeacherQuery;
import com.lhl.eduService.client.EduVodClient;
import com.lhl.eduService.client.UCenterClient;
import com.lhl.eduService.domain.*;
import com.lhl.eduService.domain.vo.CoursePublishVo;
import com.lhl.eduService.domain.vo.CourseQueryVo;
import com.lhl.eduService.domain.vo.EduChapterVo;
import com.lhl.eduService.domain.vo.EduCourseData;
import com.lhl.eduService.service.*;
import com.lhl.commonUtils.CommonResult;
import com.lhl.servicebase.JwtUtils;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于处理添加课程请求
 *
 * @author lhl
 * @since 2020-07-05
 */
@RestController
@RequestMapping("/eduservice/edu-course")
//@CrossOrigin
public class EduCourseController {

    @Autowired
    EduTeacherService eduTeacherService;

    @Autowired
    EduCourseService eduCourseService;

    @Autowired
    EduVideoService eduVideoService;

    @Autowired
    EduCourseDescriptionService descriptionService;

    @Autowired
    EduChapterService eduChapterService;

    @Autowired
    EduCommentService eduCommentService;

    //注入ucenter 远程客户端
    @Autowired
    UCenterClient uCenterClient;

    @Autowired
    EduVodClient vodClient;

    @PostMapping("add_course_info")
    public CommonResult add_course_info(@RequestBody EduCourseData courseData) {
        String id = eduCourseService.addCourseInfo(courseData);
        return CommonResult.ok().data("id", id);
    }

    @GetMapping("get_course_info/{id}")
    public CommonResult get_course_info(@PathVariable String id) {
        EduCourseData data = eduCourseService.getCourseInfo(id);
        return CommonResult.ok().data("data", data);
    }

    @DeleteMapping("removeById/{id}")
    public CommonResult removeById(@PathVariable String id) {
        eduCourseService.removeById(id);
        descriptionService.removeById(id);
        return CommonResult.ok();
    }

    @GetMapping("/getCoursePublishVoById/{courseId}")
    public CommonResult getCoursePublishVoById(@PathVariable String courseId) {
        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishVoById(courseId);
        return CommonResult.ok().data("data", coursePublishVo);
    }

    //修改课程状态为发布
    @PutMapping("/updateCourseStatus/{id}")
    public CommonResult updateCourseStatus(@PathVariable String id) {
        eduCourseService.updateCourseStatus(id);
        return CommonResult.ok();
    }

    //查询所有课程
    @GetMapping("allCourse")
    public CommonResult allCourse() {
        List<EduCourse> list = eduCourseService.list(null);
        return CommonResult.ok().data("list", list);
    }

    //分页查询课程信息
    @PostMapping("{page}/{limit}")
    public CommonResult pageQuery(@ApiParam(name = "page", value = "当前页码", required = true)
                                  @PathVariable Long page,
                                  @ApiParam(name = "limit", value = "每页记录数", required = true)
                                  @PathVariable Long limit,
                                  @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                                  @RequestBody(required = false) CourseQueryVo courseQueryVo
                                  )
    {
        Page<EduCourse> pageParam = new Page<>(page, limit);
        eduCourseService.pageQuery(pageParam,courseQueryVo);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return CommonResult.ok().data("total",total).data("records",records);
    }

    //修改课程
    @PutMapping("/updateCourse")
    public CommonResult updateCourse(@RequestBody EduCourseData courseData){
        eduCourseService.updateCourse(courseData);
        return CommonResult.ok();
    }

    //删除课程所有的关联内容,SQL层面已经做到,还需要删除阿里云上的视频
    @DeleteMapping("/deleteCourse/{id}")
    public CommonResult deleteCourse(@PathVariable String id){
        List<EduVideo> video_source_list= eduVideoService.getVideoListById(id);
        String sb = StringUtils.join(video_source_list, ",");
        vodClient.deleteById(sb);
        eduCourseService.removeById(id);
        return CommonResult.ok();
    }

    //查询8条热门课程,4个热门讲师
    @GetMapping("/hot_index")
    public CommonResult hot_course_teacher(){
        List<EduCourse> courses= eduCourseService.getHotCourse();
        List<EduTeacher> teachers= eduTeacherService.getHotTeacher();
        return CommonResult.ok().data("course",courses).data("teachers",teachers);
    }

    //前台用于分页,条件查询显示所有课程
    @PostMapping("/condition_query/{current}/{size}")
    public CommonResult condition_query(@ApiParam("查询第current页") @PathVariable Integer current,
                                        @ApiParam("查询size条记录") @PathVariable Integer size,
                                        @RequestBody(required = false) CourseQueryVo courseQueryVo){
        Page<EduCourse> coursesPage= eduCourseService.condition_query(current,size,courseQueryVo);
        return CommonResult.ok().data("courses",coursesPage.getRecords()).data("total",coursesPage.getTotal());
    }

    //前台显示课程详情,包括其章节信息
    @GetMapping("/getDetailedInfo/{id}")
    public CommonResult getDetailedInfo(@PathVariable String id){
        EduCourse course = eduCourseService.getById(id);
        List<EduChapterVo> chapterList = eduChapterService.getChapterByCourseId(id);
        EduTeacher teacher = eduTeacherService.getById(course.getTeacherId());
        EduCourseDescription description = descriptionService.getById(id);
        return CommonResult.ok().
                data("course",course).
                data("chapterList",chapterList).
                data("teacher",teacher).
                data("description",description);
    }
    //前台添加评论保存到数据库,登录信息无法直接获取,从token中解析后获取,前端把其他属性先封装好
    @PostMapping("/save_comment")
    public CommonResult save_comment(@RequestBody EduComment comment, HttpServletRequest request){
        String token = request.getHeader("token");
        Gson gson=new Gson();
        //用UCenter查出来
        String json = uCenterClient.getUserDetail(token);
        UcenterMember member = gson.fromJson(json, UcenterMember.class);
        comment.setMemberId(member.getId());
        comment.setNickname(member.getNickname());
        comment.setAvatar(member.getAvatar());
        eduCommentService.save(comment);
        return CommonResult.ok();
    }

    //分页查询当前课程的评论,把课程id传过来就行
    @GetMapping("/get_comment/{current}/{size}/{id}")
    public CommonResult get_comment(@PathVariable Integer current,
                                    @PathVariable Integer size,
                                    @PathVariable String id){
        Page<EduComment> page =new Page<EduComment>(current,size);
        QueryWrapper<EduComment> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",id);
        IPage<EduComment> commentIPage = eduCommentService.page(page, wrapper);
        List<EduComment> records = commentIPage.getRecords();
        Long total = commentIPage.getTotal();
        return CommonResult.ok().data("total",total).data("records",records);
    }

    //用于支付模块的远程调用,需要根据id返回课程信息
    @GetMapping("get_course/{id}")
    public EduCourse get_course(@PathVariable String id) {
        EduCourse course = eduCourseService.getById(id);
        return course;
    }
}

