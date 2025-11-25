package com.student;

import com.student.StudentDao;
import com.student.StudentDaoImpl;
import com.student.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student/list") // 访问路径：http://localhost:8080/StudentManagementSystem/student/list
public class StudentListServlet extends HttpServlet {
    private StudentDao studentDao = new StudentDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 查询所有学生（最新数据）
        List<Student> students = studentDao.findAll();

        // 2. 存入请求域（key 为 "students"，与 list.jsp 中遍历的 key 一致）
        req.setAttribute("students", students);

        // 3. 转发到 list.jsp（必须用转发，不能重定向，否则请求域数据丢失）
        req.getRequestDispatcher("/student/list.jsp").forward(req, resp);
    }
}
