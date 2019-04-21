package com.qingye.controller;

import com.qingye.domain.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: qingye
 * @Date: 2019/3/22 0022 19:54
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("fileUpLoad")
    public  String fileUpLoad(MultipartFile upload ,HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("文件上传");
        // 使用fileupload组件完成文件上传
        // 上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        // 判断，该路径是否存在 
        File file = new File(path);
        if(!file.exists()){
            // 创建该文件夹
            file.mkdir();
        }
        // 说明上传文件项
        // 获取上传文件的名称
        String filename = upload.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");

        filename =uuid+"_"+filename;
        // 完成文件上传
        upload.transferTo(new File(path,filename));
        return "Success";
    }
    @RequestMapping("testException")
    public String testException() throws SysException {
        System.out.println("testException执行了...");

        try {
            // 模拟异常
            int a = 10/0;
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
            // 抛出自定义异常信息
            throw new SysException("查询所有用户出现错误了...");
        }

        return "Success";
    }
}
