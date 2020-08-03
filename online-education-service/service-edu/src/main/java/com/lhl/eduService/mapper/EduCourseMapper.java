package com.lhl.eduService.mapper;

import com.lhl.eduService.domain.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhl.eduService.domain.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author lhl
 * @since 2020-07-05
 */
@Repository
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    @Select("SELECT ec.id courseId ,ec.`title`,ec.`cover`,ec.price,ec.`lesson_num` lessonNum,ecd.`description`,\n" +
            "et.`name` AS teacherName,\n" +
            "es.`title` AS oneSubject,es2.`title` AS twoSubject\n" +
            "FROM `edu_course` ec \n" +
            "LEFT JOIN `edu_course_description` ecd ON ec.id=ecd.id\n" +
            "LEFT JOIN `edu_teacher` et ON et.id=ec.`teacher_id`\n" +
            "LEFT JOIN `edu_subject` es ON ec.`subject_id`=es.`id`\n" +
            "LEFT JOIN `edu_subject` es2 ON ec.`subject_parent_id`=es2.`id`\n" +
            "WHERE ec.id=#{courseId}")
    CoursePublishVo getCoursePublishVoById(String id);

    @Update("UPDATE edu_course SET `status`='Normal' WHERE id=#{courseId}")
    void updateCourseStatus(String courseId);

}
