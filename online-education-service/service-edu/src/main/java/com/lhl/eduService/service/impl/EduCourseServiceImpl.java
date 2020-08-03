package com.lhl.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhl.commonUtils.CustomException;
import com.lhl.eduService.domain.EduCourse;
import com.lhl.eduService.domain.EduCourseDescription;
import com.lhl.eduService.domain.EduTeacher;
import com.lhl.eduService.domain.vo.CoursePublishVo;
import com.lhl.eduService.domain.vo.CourseQueryVo;
import com.lhl.eduService.domain.vo.EduCourseData;
import com.lhl.eduService.mapper.EduCourseMapper;
import com.lhl.eduService.service.EduCourseDescriptionService;
import com.lhl.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhl.eduService.service.EduSubjectService;
import com.lhl.eduService.service.EduTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lhl
 * @since 2020-07-05
 */
@Service
@Transactional
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService descriptionService;

    @Autowired
    EduSubjectService eduSubjectService;

    @Autowired
    EduTeacherService eduTeacherService;

    @Override
    public String addCourseInfo(EduCourseData courseData) {
        //将coursedata中的数据分为两部分分别插入两张表
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseData,course);

        int insert = baseMapper.insert(course);
        if (insert==0){
            throw new CustomException(20001,"插入课程信息失败");
        }

        EduCourseDescription description=new EduCourseDescription();
        BeanUtils.copyProperties(courseData,description);
        description.setId(course.getId());
        boolean save = descriptionService.save(description);
        if (!save){
            throw new CustomException(20001,"插入课程信息失败");
        }
        return course.getId();
    }

    @Override
    public EduCourseData getCourseInfo(String id) {
        EduCourseData data=new EduCourseData();

        EduCourse eduCourse = baseMapper.selectById(id);
        BeanUtils.copyProperties(eduCourse,data);

        EduCourseDescription description = descriptionService.getById(eduCourse.getId());
        BeanUtils.copyProperties(description,data);
        return data;
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String courseId) {
        CoursePublishVo coursePublishVoById = baseMapper.getCoursePublishVoById(courseId);
        return coursePublishVoById;
    }

    @Override
    public void updateCourseStatus(String id) {
        baseMapper.updateCourseStatus(id);
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQueryVo courseQueryVo) {
        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");

        if (courseQueryVo==null){
            baseMapper.selectPage(pageParam,wrapper);
            return;
        }
        String title = courseQueryVo.getTitle();
        String teacherId = courseQueryVo.getTeacherId();
        String subjectParentId = courseQueryVo.getSubjectParentId();
        String subjectId = courseQueryVo.getSubjectId();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId) ) {
            wrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            wrapper.ge("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            wrapper.ge("subject_id", subjectId);
        }
        baseMapper.selectPage(pageParam,wrapper);
    }

    @Override
    public void updateCourse(EduCourseData courseData) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription courseDescription=new EduCourseDescription();
        BeanUtils.copyProperties(courseData,eduCourse);
        BeanUtils.copyProperties(courseData,courseDescription);

        descriptionService.updateById(courseDescription);
        this.updateById(eduCourse);
    }

    @Override
    public List<EduCourse> getHotCourse() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduCourses = baseMapper.selectList(wrapper);
        return eduCourses;
    }

    @Override
    public List<EduCourse> getByTeacherId(String teacherId) {
        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("view_count");//按照浏览量降序
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courses = baseMapper.selectList(wrapper);
        return courses;
    }

    @Override
    public Page<EduCourse> condition_query(Integer current, Integer size, CourseQueryVo courseQueryVo) {
        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQueryVo.getSubjectId())) {
            wrapper.eq("subject_id", courseQueryVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseQueryVo.getSubjectParentId())) {
            wrapper.eq("subject_parent_id", courseQueryVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseQueryVo.getIs_price())) {
            if (courseQueryVo.getIs_price() == 0){
                wrapper.orderByDesc("price");
            }else {
                wrapper.orderByAsc("price");
            }
        }
        if (!StringUtils.isEmpty(courseQueryVo.getIs_focus())) {
            if (courseQueryVo.getIs_focus() == 0){
                wrapper.orderByDesc("view_count");
            }else {
                wrapper.orderByAsc("view_count");
            }
        }

        if (!StringUtils.isEmpty(courseQueryVo.getIs_update_time())) {
            if (courseQueryVo.getIs_update_time() == 0){
                wrapper.orderByDesc("gmt_modified");
            }else {
                wrapper.orderByAsc("gmt_modified");
            }
        }
        //再分页
        Page<EduCourse> page=new Page<>(current,size);
        baseMapper.selectPage(page,wrapper);

        return page;
    }


}
