package com.Controller;

import com.Entity.User;
import com.Entity.grade;
import com.Service.GradeService;
import com.Service.GradeServiceImpl.GradeServiceimpl;
import com.Util.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "GradeServlet",urlPatterns = "/gradeservlet")
public class GradeServlet extends BaseServlet {
    //创建GradeService对象
    GradeService gradeService=new GradeServiceimpl();
    public String history(HttpServletRequest request, HttpServletResponse response){
        //1.接收请求，2.调用服务，3.跳转页面
        //获取session获取当前用户信息
        User user=(User)request.getSession().getAttribute("User");
        List<grade> gradeList=gradeService.queryByuser(user.getUser_id());
        request.setAttribute("gradeList",gradeList);
        return "WEB-INF/Exam/resultTable.jsp";
    }
}
