package com.lhl.vodTest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;

/**
 * @athor:lhl
 * @create:2020-07-17 17:51
 */
public class DeleteTest {

    public static DeleteVideoResponse deleteVideo(DefaultAcsClient client,String videoId) throws Exception {
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videoId);
        return client.getAcsResponse(request);
    }

    /*请求示例*/
    public static void main(String[] argv) {
        DefaultAcsClient client = Client.initVodClient();
        DeleteVideoResponse response = new DeleteVideoResponse();
        try {
            response = deleteVideo(client,"71c0c5f0dadd469dae0887224541ea45");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}
