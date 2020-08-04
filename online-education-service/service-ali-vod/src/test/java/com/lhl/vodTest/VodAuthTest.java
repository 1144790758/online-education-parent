package com.lhl.vodTest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

/**
 * @athor:lhl
 * 使用凭证播放视频,加密视频只能使用凭证播放
 */
public class VodAuthTest {

    private static String accessKeyId="LTAI4G8cpZq3V75YT8YDhPGW";
    private static String accessKeySecret="VMukdgyyxQ14HM7WN1jsgPvGJ3G1Yg";


    /*获取播放凭证函数*/
    public static String getVideoPlayAuth(String accessKeyId, String accessKeySecret,String videoId) throws Exception {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setAuthInfoTimeout(2000L);
        request.setVideoId(videoId);
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);
        String playAuth = response.getPlayAuth();
        return playAuth;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(getVideoPlayAuth(accessKeyId, accessKeySecret, "70f6801a55bf4983b15be32db5abadc6"));
    }
}
