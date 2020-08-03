package com.lhl.eduService.service;

import com.lhl.eduService.domain.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lhl.eduService.domain.vo.EduChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lhl
 * @since 2020-07-05
 */
public interface EduChapterService extends IService<EduChapter> {

    List<EduChapterVo> getChapterByCourseId(String courseId);

    boolean removeChapterById(String chapterId);
}
