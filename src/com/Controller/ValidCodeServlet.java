package com.Controller;

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

@WebServlet("/validCode")
public class ValidCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 200, height = 30;
        //创建对象在内存中存储图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //绘制图片
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.drawRect(0, 0, width - 1, height - 1);
        //验证码字符集
        String codeSet = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
        //随机角标 绘制验证码
        int codeNum = 4;//验证码字符个数
        int offset = 5, offsetY = 18;
        int fontSize = 20;
        Random random = new Random();
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, fontSize));
        StringBuilder validNum = new StringBuilder();
        for (int i = 0; i < codeNum; i++) {
            int index = random.nextInt(codeSet.length());
            char ch = codeSet.charAt(index);
            validNum.append(ch);
            g.drawString(ch + "", width / (codeNum) * i + offset, offsetY);
        }
        //将验证码存入session
        request.getSession().setAttribute("checkCode_session",validNum.toString());

        //画干扰线
        int lineNum = 10;
        g.setColor(Color.BLUE);
        for (int i = 0; i < lineNum; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }

        ImageIO.write(image, "jpg", response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
