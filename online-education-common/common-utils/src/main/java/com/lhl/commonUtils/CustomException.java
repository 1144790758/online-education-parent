package com.lhl.commonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @athor:lhl
 */
@Data
@NoArgsConstructor
public class CustomException extends RuntimeException{
    private Integer code;
    private String msg;

    public CustomException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
