<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTL标签库 -->
<html>
<head>
    <title>学生列表</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>学生信息管理</h2>
        <!-- 添加学生按钮 -->
        <a href="${pageContext.request.contextPath}/student/add.jsp" class="btn btn-success">添加学生</a>
    </div>

    <!-- 学生表格 -->
    <table class="table table-striped table-hover">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
            <th>学号</th>
            <th>专业</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
            <td>${student.gender}</td>
            <td>${student.studentId}</td>
            <td>${student.major}</td>
            <td>
                <!-- 编辑按钮 -->
                <a href="${pageContext.request.contextPath}/student/edit?id=${student.id}" class="btn btn-sm btn-primary">编辑</a>
                <form action="${pageContext.request.contextPath}/student/delete" method="post" onsubmit="return confirm('确定删除吗？')">
                    <input type="hidden" name="id" value="${student.id}"> <%-- 传递学生ID --%>
                    <button type="submit" class="btn btn-sm btn-primary">删除</button>
                </form>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>