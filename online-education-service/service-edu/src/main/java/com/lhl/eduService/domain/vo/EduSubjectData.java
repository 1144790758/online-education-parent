package com.lhl.eduService.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 课程分类返回的数据封装对象
 * @athor:lhl
 */
@Data
public class EduSubjectData {
    private String id;
    private String label;
    private List<EduSubjectData> children;
}
