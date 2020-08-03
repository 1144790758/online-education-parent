package com.lhl.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhl.commonUtils.CustomException;
import com.lhl.eduService.domain.EduChapter;
import com.lhl.eduService.domain.EduVideo;
import com.lhl.eduService.domain.vo.EduChapterVo;
import com.lhl.eduService.domain.vo.EduVideoVo;
import com.lhl.eduService.mapper.EduChapterMapper;
import com.lhl.eduService.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhl.eduService.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lhl
 * @since 2020-07-05
 */
@Service
@Transactional
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService eduVideoService;

    @Override
    public List<EduChapterVo> getChapterByCourseId(String courseId) {
        ArrayList<EduChapterVo> list=new ArrayList<>();

        QueryWrapper<EduChapter> wrapper=new QueryWrapper();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);

        for (EduChapter eduChapter : eduChapters) {
            String chapterId = eduChapter.getId();
            QueryWrapper<EduVideo> voWrapper=new QueryWrapper();
            voWrapper.eq("chapter_id",chapterId);
            voWrapper.eq("course_id",courseId);
            List<EduVideo> eduVideos = eduVideoService.list(voWrapper);

            EduChapterVo eduChapterVo = new EduChapterVo();
            eduChapterVo.setId(eduChapter.getId());
            eduChapterVo.setTitle(eduChapter.getTitle());
            ArrayList<EduVideoVo> list2=new ArrayList<>();
            for (EduVideo eduVideo : eduVideos) {
                EduVideoVo video=new EduVideoVo();
                video.setTitle(eduVideo.getTitle());
                video.setId(eduVideo.getId());
                video.setFree(eduVideo.getIsFree());
                video.setVideoSourceId(eduVideo.getVideoSourceId());
                list2.add(video);
            }
            eduChapterVo.setVideoVoList(list2);
            list.add(eduChapterVo);
        }

        return list;
    }

    @Override
    public boolean removeChapterById(String chapterId) {
        //根据id查询是否存在视频，如果有则提示用户尚有子节点
        if(eduVideoService.getCountByChapterId(chapterId)>0){
            throw new CustomException(20001,"该分章节下存在视频课程，请先删除视频课程");
        }

        Integer result = baseMapper.deleteById(chapterId);
        return null != result && result > 0;

    }
}
