package com.lhl.eduService.config;

import com.lhl.commonUtils.CommonResult;
import com.lhl.commonUtils.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @athor:lhl
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CommonResult error(Exception e){
        e.printStackTrace();
        return CommonResult.error().message("统一异常处理");

    }

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public CommonResult customException(CustomException e){
        log.error(e.getMsg());
        CommonResult error = CommonResult.error();
        error.setCode(e.getCode());
        error.setMessage(e.getMsg());
        return error;
    }
}
