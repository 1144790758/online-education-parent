package com.lhl.eduService.mapper;

import com.lhl.eduService.domain.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author lhl
 * @since 2020-06-24
 */
@Repository
@Mapper
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

    @Select("select * from edu_teacher")
    List<EduTeacher> findall();
}
