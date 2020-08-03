package com.lhl.eduService.mapper;

import com.lhl.eduService.domain.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author lhl
 * @since 2020-07-04
 */
@Repository
@Mapper
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

}
