package com.lhl.eduService.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @athor:lhl
 * @create:2020-07-09 21:33
 */
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class CourseQueryVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    private String subjectId;

    @ApiModelProperty(value = "是否按照关注度排序,1为降序,0为升序")
    private Integer is_focus;

    @ApiModelProperty(value = "是否按照更新时间排序,1为降序,0为升序")
    private Integer is_update_time;

    @ApiModelProperty(value = "是否按照价格排序,1为降序,0为升序")
    private Integer is_price;
}
