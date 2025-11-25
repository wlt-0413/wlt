package com.student;

import com.student.LoginDao;
import com.student.LoginDaoImpl;
import com.student.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login") // 访问路径：http://localhost:8080/StudentManagementSystem/login
public class LoginServlet extends HttpServlet {
    private LoginDao loginDao = new LoginDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收前端参数（解决中文乱码）
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. 调用DAO验证登录
        Admin admin = loginDao.login(username, password);

        // 3. 处理结果
        if (admin != null) {
            // 登录成功：跳转到学生列表页面
            resp.sendRedirect(req.getContextPath() + "/student/list");
        } else {
            // 登录失败：回显错误信息
            req.setAttribute("errorMsg", "用户名或密码错误！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}