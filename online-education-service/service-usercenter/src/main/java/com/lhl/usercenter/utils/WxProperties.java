package com.lhl.usercenter.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @athor:lhl
 */
@Component
@Data
public class WxProperties implements InitializingBean {

    @Value("${wx.open.appid}")
    private String appid;

    @Value("${wx.open.appsecret}")
    private String appsecret;

    @Value("${wx.open.redirecturl}")
    private String redirecturl;

    public static String AppId;
    public static String AppSecret;
    public static String RedirectUrl;

    @Override
    public void afterPropertiesSet() throws Exception {
        AppId=appid;
        AppSecret=appsecret;
        RedirectUrl=redirecturl;
    }
}
