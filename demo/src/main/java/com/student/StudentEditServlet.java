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

@WebServlet("/student/edit") // 映射路径必须是 /student/edit
public class StudentEditServlet extends HttpServlet {
    private StudentDao studentDao = new StudentDaoImpl();

    // 跳转编辑页面（回显数据）- 处理 GET 请求
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. 接收 id 参数（关键：参数名是 "id"，与按钮 URL 一致）
            String idStr = req.getParameter("id");
            if (idStr == null || idStr.trim().isEmpty()) {
                req.setAttribute("errorMsg", "学生ID不能为空！");
                req.getRequestDispatcher("/student/list").forward(req, resp);
                return;
            }
            Integer id = Integer.parseInt(idStr); // 转换为整数

            // 2. 根据 ID 查询学生（确保 DAO 方法正确）
            Student student = studentDao.findById(id);
            if (student == null) {
                req.setAttribute("errorMsg", "该学生不存在！");
                req.getRequestDispatcher("/student/list").forward(req, resp);
                return;
            }

            // 3. 存入请求域，转发到编辑页面
            req.setAttribute("student", student);
            // 编辑页面路径必须正确（若 edit.jsp 在 student 目录下）
            req.getRequestDispatcher("/student/edit.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMsg", "学生ID格式错误！");
            req.getRequestDispatcher("/student/list").forward(req, resp);
        }
    }

    // 提交修改 - 处理 POST 请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            // 1. 接收参数（包含隐藏域的 id）
            String idStr = req.getParameter("id");
            String name = req.getParameter("name");
            String ageStr = req.getParameter("age");
            String gender = req.getParameter("gender");
            String studentId = req.getParameter("studentId");
            String major = req.getParameter("major");

            // 非空校验
            if (idStr == null || name == null || ageStr == null || studentId == null) {
                req.setAttribute("errorMsg", "必填项不能为空！");
                // 跳转回编辑页面，回显错误信息（携带 id）
                req.getRequestDispatcher("/student/edit?id=" + idStr).forward(req, resp);
                return;
            }

            Integer id = Integer.parseInt(idStr);
            Integer age = Integer.parseInt(ageStr);

            // 2. 封装学生对象
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setAge(age);
            student.setGender(gender);
            student.setStudentId(studentId);
            student.setMajor(major);

            // 3. 执行修改
            studentDao.update(student);

            // 4. 重定向到列表页（刷新最新数据）
            resp.sendRedirect(req.getContextPath() + "/student/list");
        } catch (NumberFormatException e) {
            req.setAttribute("errorMsg", "ID或年龄格式错误！");
            req.getRequestDispatcher("/student/list").forward(req, resp);
        }
    }
}