package com.lhl.eduService.service;

import com.lhl.eduService.domain.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author lhl
 * @since 2020-07-05
 */
public interface EduVideoService extends IService<EduVideo> {

    //统计出该章节有多少个视频
    int getCountByChapterId(String chapterId);

    List<EduVideo> getVideoListById(String id);
}
