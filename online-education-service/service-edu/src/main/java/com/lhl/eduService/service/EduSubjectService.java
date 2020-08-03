package com.lhl.eduService.service;

import com.lhl.eduService.domain.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lhl.eduService.domain.vo.EduSubjectData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author lhl
 * @since 2020-07-04
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addSubject(MultipartFile file);

    List<EduSubjectData> listSubject();
}
