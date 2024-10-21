<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Grades</title>
</head>
<body>
<h2>Your Grades:</h2>
<table >
    <thead>
    <tr>
        <th>Course</th>
        <th>Grade</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="grade" items="${grades}">
        <tr>
            <td>${grade.courseName}</td>
            <td>${grade.grade}</td>
        </tr>
    </c:forEach>
    <c:if test="${empty grades}">
        <tr>
            <td colspan="2">No grades available</td>
        </tr>
    </c:if>
    </tbody>
</table>
</body>
</html>
