package com.lhl.eduService.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @athor:lhl
 * @create:2020-07-04 21:35
 */
@Data
public class ExcelData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
