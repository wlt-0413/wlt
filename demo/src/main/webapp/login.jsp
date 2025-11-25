<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理系统 - 登录</title>
    <!-- 引入Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .login-container {
            margin-top: 100px;
            max-width: 400px;
        }
    </style>
</head>
<body>
<div class="container login-container">
    <div class="card shadow">
        <div class="card-header bg-primary text-white text-center">
            <h3>学生管理系统</h3>
        </div>
        <div class="card-body p-4">
            <!-- 错误信息提示 -->
            <% if (request.getAttribute("errorMsg") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("errorMsg") %>
            </div>
            <% } %>

            <!-- 登录表单 -->
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">用户名</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">密码</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">登录</button>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>