package com.lhl.eduService.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * vo对象是用于与前端进行数据交互的
 * @athor:lhl
 * @create:2020-07-07 18:03
 */
@Data
@ApiModel(value = "课时信息")
public class EduVideoVo {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
    private Integer sort;
    private String videoSourceId;

    @ApiModelProperty(value = "原始文件名称")
    private String videoOriginalName;
}
