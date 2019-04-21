package com.qingyemarket.manager.controller;

import com.qingyemarket.entity.Result;
import com.qingyemarket.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: qingye
 * @Date: 2019/4/16 0016 19:22
 * @Version 1.0
 */
@RestController
public class UploadController {
    @Value("${FILE_SERVER_URL}")
    private String file_server_url;
    @RequestMapping("/upload")
    public Result upload(MultipartFile file){
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件类型
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            FastDFSClient client = new FastDFSClient("classpath:/config/fdfs_client.conf");
           //将文件转化为字节码，第二个元素的意思是创一个文件类型，得到文件的id
            String fileId = client.uploadFile(file.getBytes(), extName);
          // 图片完整地址
            String url = file_server_url+fileId;
            return new Result(true,url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"上传失败");
        }

    }
}
