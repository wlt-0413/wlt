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

@WebServlet("/student/delete") // URL映射不变
public class StudentDeleteServlet extends HttpServlet {
    private StudentDao studentDao = new StudentDaoImpl();

    // 改为doPost，接收delete.jsp的表单提交
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idStr = req.getParameter("id");
            if (idStr == null || idStr.trim().isEmpty()) {
                req.setAttribute("errorMsg", "学生ID不能为空！");
                req.getRequestDispatcher("/student/list").forward(req, resp);
                return;
            }
            Integer id = Integer.parseInt(idStr);

            Student student = studentDao.findById(id);
            if (student == null) {
                req.setAttribute("errorMsg", "该学生不存在，无法删除！");
                req.getRequestDispatcher("/student/list").forward(req, resp);
                return;
            }

            // 调用删除方法（可能抛出异常）
            studentDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/student/list");
        } catch (NumberFormatException e) {
            req.setAttribute("errorMsg", "学生ID格式错误！");
            req.getRequestDispatcher("/student/list").forward(req, resp);
        } catch (RuntimeException e) { // 捕获删除失败的异常
            req.setAttribute("errorMsg", e.getMessage()); // 显示具体错误原因
            req.getRequestDispatcher("/student/list").forward(req, resp);
        }
    }
    // 若有人直接访问/student/delete（GET请求），重定向到列表页
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/student/list");
    }
}