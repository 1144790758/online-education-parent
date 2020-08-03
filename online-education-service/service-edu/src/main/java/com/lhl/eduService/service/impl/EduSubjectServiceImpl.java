package com.lhl.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhl.eduService.config.SubjectExcelListener;
import com.lhl.eduService.domain.EduSubject;
import com.lhl.eduService.domain.vo.EduSubjectData;
import com.lhl.eduService.domain.vo.ExcelData;
import com.lhl.eduService.mapper.EduSubjectMapper;
import com.lhl.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lhl
 * @since 2020-07-04
 */
@Service
@Transactional
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Override
    public void addSubject(MultipartFile file) {
        InputStream inputStream=null;
        try {
             inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EasyExcel.read(inputStream, ExcelData.class,new SubjectExcelListener(this)).doReadAll();
    }

    @Override
    public List<EduSubjectData> listSubject() {
        ArrayList<EduSubjectData> data = new ArrayList<>();
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("parent_id","0");
        List<EduSubject> oneSubject = baseMapper.selectList(queryWrapper);

        String id="";
        List<EduSubject> twoSubject=null;
        for (EduSubject eduSubject : oneSubject) {
            QueryWrapper<EduSubject> queryWrapper2=new QueryWrapper<>();
            id=eduSubject.getId();
            queryWrapper2.eq("parent_id",id);
            twoSubject = baseMapper.selectList(queryWrapper2);

            EduSubjectData data1=new EduSubjectData();
            data1.setId(id);
            data1.setLabel(eduSubject.getTitle());
            ArrayList<EduSubjectData> listChildren=new ArrayList<>();
            for (EduSubject subject : twoSubject) {
                EduSubjectData children=new EduSubjectData();
                children.setId(subject.getId());
                children.setLabel(subject.getTitle());
                listChildren.add(children);
            }
            data1.setChildren(listChildren);
            data.add(data1);
        }
        return data;
    }
}
