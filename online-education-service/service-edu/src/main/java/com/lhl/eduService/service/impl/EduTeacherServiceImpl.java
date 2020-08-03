package com.lhl.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhl.eduService.domain.EduTeacher;
import com.lhl.eduService.mapper.EduTeacherMapper;
import com.lhl.eduService.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author lhl
 * @since 2020-06-24
 */
@Service
@Transactional
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public List<EduTeacher> findAllTeacher() {
        List<EduTeacher> teacherList = baseMapper.selectList(new QueryWrapper<EduTeacher>());
        return teacherList;
    }

    @Override
    public List<EduTeacher> getHotTeacher() {
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        List<EduTeacher> eduTeachers = baseMapper.selectList(wrapper);
        return eduTeachers;
    }
}
