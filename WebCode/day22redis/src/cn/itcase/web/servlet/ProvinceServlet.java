package cn.itcase.web.servlet;

import cn.itcase.domain.Province;
import cn.itcase.service.Impl.ProvinceServiceImpl;
import cn.itcase.service.ProvinceService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/25 0025 18:35
 * @Version 1.0
 */
@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // response.setContentType("application/json;charset=utf-8");
        ProvinceService service = new ProvinceServiceImpl();
        //调用方法
        //List<Province> list = service.findAll();
        String json = service.findAllJson();
       /* ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);*/
        System.out.println(json);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
