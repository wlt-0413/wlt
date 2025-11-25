package com.student;

import com.student.Student;
import java.util.List;

public interface StudentDao {
    // 查询所有学生
    List<Student> findAll();
    // 根据ID查询学生
    Student findById(Integer id);
    // 添加学生
    int add(Student student);
    // 修改学生
    int update(Student student);
    // 删除学生
    int delete(Integer id);
}