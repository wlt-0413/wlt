<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除学生 - 确认</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="card shadow" style="max-width: 500px; margin: 0 auto;">
        <div class="card-header bg-danger text-white">
            <h4>删除确认</h4>
        </div>
        <div class="card-body">
            <%-- 接收列表页传递的学生ID --%>
            <% String id = request.getParameter("id"); %>
            <% if (id == null || id.trim().isEmpty()) { %>
            <div class="alert alert-warning">参数错误！未指定要删除的学生ID。</div>
            <a href="${pageContext.request.contextPath}/student/list" class="btn btn-secondary">返回列表</a>
            <% } else { %>
            <p>确定要删除ID为 <strong><%= id %></strong> 的学生吗？删除后数据无法恢复！</p>

            <%-- 用POST表单提交删除请求（避免URL传参风险） --%>
            <form action="${pageContext.request.contextPath}/student/delete" method="post">
                <input type="hidden" name="id" value="<%= id %>">
                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-danger flex-1">确认删除</button>
                    <a href="${pageContext.request.contextPath}/student/list" class="btn btn-secondary flex-1">取消</a>
                </div>
            </form>
            <% } %>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>