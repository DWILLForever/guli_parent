package com.dwill.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.dwill.oss.service.OssService;
import com.dwill.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;
@Service
public class OssServiceImpl implements OssService {
    //上传文件到oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //工具类获取值
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名
            String filename = file.getOriginalFilename();
            //在文件中，添加唯一值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            filename=uuid+filename;
            //把文件按日期分类
            String dataPath=new DateTime().toString("yyyy/MM/dd");
            filename=dataPath+"/"+filename;
            //调用OSSClient
            ossClient.putObject(bucketName,filename,inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            //把上传文件的路径返回
            String url="http://"+bucketName+"."+endPoint+"/"+filename;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
