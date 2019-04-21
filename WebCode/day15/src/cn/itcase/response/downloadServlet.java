package cn.itcase.response;

import cn.itcase.response.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: qingye
 * @Date: 2019/2/12 0012 18:40
 * @Version 1.0
 */
@WebServlet("/downloadServlet")
public class downloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数，文件名称
        String filename = request.getParameter("filename");
        //2.使用字节流将文件加载进内存
        ServletContext servletContext = this.getServletContext();
        //3.通过Servletcontext找到服务器文件的真实路径
        String realPath = servletContext.getRealPath("/img/" + filename);
        //使用字节流关联
        FileInputStream fis = new FileInputStream(realPath);
        //4.设置响应头类型

        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type",mimeType);
        //5.设置响应头的打开方式(attachment以附件形式打开文件)
        String agent = request.getHeader("user-agent");
        filename = DownLoadUtils.getFileName(agent,filename);
        //解决中文字乱码
        response.setHeader("content-disposition","attachment;filename="+filename);
        //6.将输入流的数据写到输出流中
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff = new byte[8*1024];
        int len = 0;
        while ((len= fis.read(buff))!=-1){
            sos.write(buff,0,len);
        }
        fis.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
