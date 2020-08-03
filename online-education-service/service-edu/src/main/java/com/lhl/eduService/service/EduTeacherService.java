package com.lhl.eduService.service;

import com.lhl.eduService.domain.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lhl
 * @since 2020-06-24
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> findAllTeacher();

    List<EduTeacher> getHotTeacher();
}
