package com.lhl.eduService.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @athor:lhl
 * @create:2020-07-07 18:00
 */
@Data
@ApiModel(value = "章节信息")
public class EduChapterVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;
    private List<EduVideoVo> videoVoList = new ArrayList<>();
}
