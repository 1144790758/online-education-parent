package com.lhl.usercenter.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @athor:lhl
 * @create:2020-07-23 15:05
 */
@Data
public class UcenterLoginInfoVo {
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "会员id")
    private String id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;
}
