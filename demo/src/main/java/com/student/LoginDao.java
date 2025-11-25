package com.student;

import com.student.Admin;

public interface LoginDao {
    // 根据用户名和密码查询管理员
    Admin login(String username, String password);
}