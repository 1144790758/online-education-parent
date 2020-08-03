package com.lhl.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhl.eduService.domain.EduVideo;
import com.lhl.eduService.mapper.EduVideoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhl.eduService.service.EduVideoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author lhl
 * @since 2020-07-05
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public int getCountByChapterId(String chapterId) {
        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        Integer count = baseMapper.selectCount(wrapper);
        return count;
    }

    @Override
    public List<EduVideo> getVideoListById(String id) {
        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",id);
        List<EduVideo> selectList = baseMapper.selectList(wrapper);
        return selectList;
    }

}
