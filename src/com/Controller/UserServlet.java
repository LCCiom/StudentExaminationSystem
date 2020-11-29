package com.Controller;

import com.Entity.User;
import com.Service.UserService;
import com.Service.UserServiceImpl.UserServiceimpl;
import com.Util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UserServlet",urlPatterns = "/userServlet")
public class UserServlet extends BaseServlet {
    //生成服务对象
    UserService userService = new UserServiceimpl();

    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收请求，调用服务，跳转界面
        String username = request.getParameter("username");
        String userPassword = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        if (username == null || userPassword == null || checkCode == null) {
            //存储提示信息到request
            //request.setAttribute("login_error", "用户名、密码及验证码不能为空");
            //登录失败，仍在原界面
            return "login.jsp";
        }
        //通过Session共享资源获取生成的验证码
        HttpSession session = request.getSession();
        String right_code = (String) session.getAttribute("checkCode_session");
        //忽略大小写验证验证码
        if (checkCode.equalsIgnoreCase(right_code)) {
            //调用服务，取出对应用户名的用户id与密码
            User user = userService.queryByUserName(username);
            //判断密码是否一致
            if (user != null && user.getPassword().equals(userPassword)) {
                //使用Session存入user信息
                session = request.getSession();
                session.setAttribute("User", user);
                request.setAttribute("userName", username);
                return "WEB-INF/Exam/studentIndex.jsp";
            }
            request.setAttribute("msg","用户名或密码错误！");
        } else {
            request.setAttribute("msg","验证码错误！");
        }
        //登录失败，仍在原界面
        return "login.jsp";
    }
    public String logout(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if ("userCode".equals(cookie.getName())){
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            if ("userPassword".equals(cookie.getName())){
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        request.getSession().invalidate();
        return "login.jsp";
    }
    public  String register(HttpServletRequest request,HttpServletResponse response){
        //接收请求，调用服务，跳转界面
        //将前端的数据整合在一个user对象中
        User user=new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setAge(request.getParameter("age"));
        user.setSex(request.getParameter("sex"));
        user.setRole(request.getParameter("role"));
        user.setTelephone(request.getParameter("telephone"));
        //调用服务
        userService.insert(user);
        //跳转界面
        return "login.jsp";
    }
}
