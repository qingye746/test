package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 分页查询
 *
 * @Author: qingye
 * @Date: 2019/3/1 0001 16:23
 * @Version 1.0
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();

    private FavoriteService favoriteService = new FavoriteServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String rname = request.getParameter("rname");
        //tomcat7不会自动解决乱码问题，需要手动修改格式
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 1;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        int pageSize = 5;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);

        //序列化为json发送
        writevalue(pb, response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        Route route = routeService.findOne(rid);
        writevalue(route, response);
    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        //获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        //用户的id
        int uid;
        if (user == null) {
            //未登录
            return;
        } else {
            uid = user.getUid();

        }
        //调用service添加
        favoriteService.add(rid, uid);
    }


    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        //获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        //用户的id
        int uid;
        if (user == null) {
            //未登录
            uid = 0;
        } else {
            uid = user.getUid();

        }
        boolean flag = favoriteService.isFavorite(rid, uid);
        writevalue(flag, response);
    }

}
