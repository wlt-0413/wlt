package com.student;

import com.student.StudentDao;
import com.student.Student;
import com.student.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM student";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                student.setStudentId(rs.getString("student_id"));
                student.setMajor(rs.getString("major"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return students;
    }

    @Override
    public Student findById(Integer id) {
        Student student = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM student WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                student.setStudentId(rs.getString("student_id"));
                student.setMajor(rs.getString("major"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        return student;
    }

    @Override
    public int add(Student student) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO student (name, age, gender, student_id, major) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getGender());
            pstmt.setString(4, student.getStudentId());
            pstmt.setString(5, student.getMajor());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return rows;
    }

    @Override
    public int update(Student student) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE student SET name=?, age=?, gender=?, student_id=?, major=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getGender());
            pstmt.setString(4, student.getStudentId());
            pstmt.setString(5, student.getMajor());
            pstmt.setInt(6, student.getId());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return rows;
    }

    @Override
    public int delete(Integer id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // 开启事务
            String sql = "DELETE FROM student WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rows = pstmt.executeUpdate();
            conn.commit(); // 提交事务
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // 回滚事务
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            // 可自定义异常类型，便于上层捕获
            throw new RuntimeException("删除失败：" + e.getMessage());
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return rows;
    }
}