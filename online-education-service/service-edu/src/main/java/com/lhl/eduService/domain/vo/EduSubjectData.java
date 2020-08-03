package com.lhl.eduService.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 课程分类返回的数据封装对象
 * @athor:lhl
 * @create:2020-07-05 15:45
 */
@Data
public class EduSubjectData {
    private String id;
    private String label;
    private List<EduSubjectData> children;
}
