<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    Username: <input type="text" name="first_name"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Login">
</form>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
</body>
</html>
