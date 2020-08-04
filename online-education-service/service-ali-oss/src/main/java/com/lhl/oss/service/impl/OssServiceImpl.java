package com.lhl.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lhl.oss.service.OssService;
import com.lhl.oss.utils.OssPropertisUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @athor:lhl
 */
@Service
public class OssServiceImpl implements OssService {


    @Override
    public String upLoadAvatar(MultipartFile file) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = OssPropertisUtil.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，
        // 创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = OssPropertisUtil.ACCESS_KEY_ID;
        String accessKeySecret = OssPropertisUtil.ACCESS_KEY_SECRET;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            ossClient.shutdown();
            return null;
        }
        String filename = file.getOriginalFilename();
        //随机生成文件名
//        Date now=new Date();
//        Long time = now.getTime();
//        String str = time.toString();
//        filename= str.substring(str.length()-4,str.length())+UUID.randomUUID().toString().replaceAll("-","").substring(4)+filename;
//        System.out.println(filename);


        //按照日期分文件夹存储
        //2020/1/11.jpg
        //使用joda工具生成时间
        String timeStr = new DateTime().toString("yyyy-MM-dd/");
        filename=UUID.randomUUID().toString().replaceAll("-","").substring(6)+filename;
        filename=timeStr+filename;

        ossClient.putObject(OssPropertisUtil.BUCKET_NAME, filename, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        //手动拼接图片的url
        //https://edu-avatar1.oss-cn-beijing.aliyuncs.com/timg.jpg
        String url="https://"+OssPropertisUtil.BUCKET_NAME+"."+endpoint+"/"+filename;

        return url;
    }
}
