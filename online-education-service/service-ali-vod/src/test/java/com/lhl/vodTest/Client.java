package com.lhl.vodTest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @athor:lhl
 * @create:2020-07-17 17:36
 */
public class Client {

    private static String accessKeyId="LTAI4G8cpZq3V75YT8YDhPGW";
    private static String accessKeySecret="VMukdgyyxQ14HM7WN1jsgPvGJ3G1Yg";
    //初始化
    public static DefaultAcsClient initVodClient() {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
