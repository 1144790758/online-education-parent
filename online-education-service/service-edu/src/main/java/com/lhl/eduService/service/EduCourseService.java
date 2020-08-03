package com.lhl.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhl.eduService.domain.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lhl.eduService.domain.EduTeacher;
import com.lhl.eduService.domain.vo.CoursePublishVo;
import com.lhl.eduService.domain.vo.CourseQueryVo;
import com.lhl.eduService.domain.vo.EduCourseData;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lhl
 * @since 2020-07-05
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(EduCourseData courseData);

    EduCourseData getCourseInfo(String id);

    CoursePublishVo getCoursePublishVoById(String courseId);

    void updateCourseStatus(String id);

    void pageQuery(Page<EduCourse> pageParam, CourseQueryVo courseQueryVo);

    void updateCourse(EduCourseData courseData);

    List<EduCourse> getHotCourse();

    List<EduCourse> getByTeacherId(String teacherId);

    Page<EduCourse> condition_query(Integer current, Integer size, CourseQueryVo courseQueryVo);

}
