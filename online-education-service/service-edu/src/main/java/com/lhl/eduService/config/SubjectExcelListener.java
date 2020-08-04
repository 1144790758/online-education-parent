package com.lhl.eduService.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhl.commonUtils.CustomException;
import com.lhl.eduService.domain.EduSubject;
import com.lhl.eduService.domain.vo.ExcelData;
import com.lhl.eduService.service.EduSubjectService;

/**
 * @athor:lhl
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelData> {

    private EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //每次一行一行读取内容,都会执行此方法,只不过读取后excelData会是下一行的
    @Override
    public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
        if(excelData == null) {
            throw new CustomException(20001,"添加失败");
        }

        //这里就会传入读取的Excel文件内容
        //但是添加一级分类时需要判断数据库中是否有重复的
        String oneSubjectName = excelData.getOneSubjectName();//得到一级标题内容
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("title",oneSubjectName);
        EduSubject one = subjectService.getOne(queryWrapper);
        if (one!=null){//说明数据库中有该一级标题,获取它的id并将其作为二级标题的parent_id插入数据
            String parent_id = one.getId();
            EduSubject twoSubject=new EduSubject();
            twoSubject.setTitle(excelData.getTwoSubjectName());
            twoSubject.setParentId(parent_id);
            subjectService.save(twoSubject);
        }else {//数据库中没有该一级标题,添加进去,然后得到他的id将其作为二级标题的parent_id插入数据
            EduSubject oneSubject=new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(oneSubjectName);
            subjectService.save(oneSubject);

            String parent_id = oneSubject.getId();
            EduSubject twoSubject=new EduSubject();
            twoSubject.setParentId(parent_id);
            twoSubject.setTitle(excelData.getTwoSubjectName());
            subjectService.save(twoSubject);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
