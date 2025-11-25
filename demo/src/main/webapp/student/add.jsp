<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="card shadow" style="max-width: 600px; margin: 0 auto;">
        <div class="card-header bg-success text-white">
            <h4>添加学生信息</h4>
        </div>
        <div class="card-body p-4">
            <form action="${pageContext.request.contextPath}/student/add" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">姓名</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="age" class="form-label">年龄</label>
                    <input type="number" class="form-control" id="age" name="age" min="1" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">性别</label>
                    <div>
                        <input type="radio" name="gender" value="男" checked> 男
                        <input type="radio" name="gender" value="女" class="ms-3"> 女
                    </div>
                </div>
                <div class="mb-3">
                    <label for="studentId" class="form-label">学号</label>
                    <input type="text" class="form-control" id="studentId" name="studentId" required>
                </div>
                <div class="mb-3">
                    <label for="major" class="form-label">专业</label>
                    <input type="text" class="form-control" id="major" name="major" required>
                </div>
                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-success flex-1">提交</button>
                    <a href="${pageContext.request.contextPath}/student/list" class="btn btn-secondary flex-1">取消</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>