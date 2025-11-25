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

@WebServlet("/student/add") // 确保映射路径正确（必须是 /student/add）
public class StudentAddServlet extends HttpServlet {
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 1. 接收参数（保留原有代码）
        String name = req.getParameter("name");
        String ageStr = req.getParameter("age");
        String gender = req.getParameter("gender");
        String studentId = req.getParameter("studentId");
        String major = req.getParameter("major");

        // 非空校验（避免无效数据）
        if (name == null || name.trim().isEmpty() || ageStr == null || studentId == null || studentId.trim().isEmpty()) {
            req.setAttribute("errorMsg", "姓名、年龄、学号不能为空！");
            // 跳转回添加页面，回显错误信息
            req.getRequestDispatcher("/student/add.jsp").forward(req, resp);
            return;
        }

        Integer age = Integer.parseInt(ageStr);
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setGender(gender);
        student.setStudentId(studentId);
        student.setMajor(major);

        // 2. 执行添加
        studentDao.add(student);

        // 3. 关键：重定向到 StudentListServlet（触发重新查询数据）
        // 错误写法：resp.sendRedirect("/student/list.jsp");（直接跳JSP，不查询）
        // 正确写法：跳转到 Servlet 路径
        resp.sendRedirect(req.getContextPath() + "/student/list");
    }
}