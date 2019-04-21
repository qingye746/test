package cm.itcase.session;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Author: qingye
 * @Date: 2019/2/12 0012 15:48
 * @Version 1.0
 */
@WebServlet("/verificationCode")
public class verificationCode extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //设置背景图片大小
        Graphics g = image.getGraphics();
        //画笔对象
        g.setColor(Color.pink);
        //设置画笔颜色
        g.fillRect(0, 0, width, height);
        //填充背景色
        g.setColor(Color.blue);
        //设置画笔颜色
        g.drawRect(0, 0, width - 1, height - 1);
        //画边框
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        Random ran = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);
            sb.append(ch);
            g.drawString(ch + "", width / 5 * i, height / 2);
        }
        String check_code = sb.toString();
        request.getSession().setAttribute("check_session",check_code);
        //画干扰线
        g.setColor(Color.green);
        for (int i = 0; i < 10; i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y2 = ran.nextInt(height);
            int y1 = ran.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }
        //输出图片
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
