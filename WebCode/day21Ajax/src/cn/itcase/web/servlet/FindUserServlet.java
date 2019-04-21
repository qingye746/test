package cn.itcase.web.servlet;

import cn.itcase.domain.Person;
import cn.itcase.service.UserService;
import cn.itcase.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/23 0023 18:50
 * @Version 1.0
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//she
        //1.获取用户名
        String username = request.getParameter("username");
        //设置响应格式为json
        response.setContentType("application/json;charset=utf-8");

        HashMap<String, Object> map = new HashMap<>();
      /*  boolean flag = false;
        UserService service = new UserServiceImpl();
        List<Person> users = service.findAll();
        for (Person user : users) {
            if (username.equals(user.getName())){
                //存在
               flag =true;
                break;
            }

        }
        if(flag){
            //存在
            map.put("userExsit",true);
            //重复提示信息
            map.put("msg","用户名已存在");
        }else {
            //不存在

            map.put("userExsit",false);
            //提示信息
            map.put("msg","用户名可用");
        }*/
        //判断获取的数据是否和数据库的数据吻合
        if("tom".equals(username)){
            //存在
            map.put("userExsit",true);
            //重复提示信息
            map.put("msg","用户名已存在");
        }else{
            //不存在

            map.put("userExsit",false);
            //提示信息
            map.put("msg","用户名可用");
        }

        ObjectMapper mapper = new ObjectMapper();
        //将数据传到客户端
        mapper.writeValue(response.getWriter(),map);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
