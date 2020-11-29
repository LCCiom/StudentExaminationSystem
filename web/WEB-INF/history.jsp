<%--
  Created by IntelliJ IDEA.
  User: zyq914014125
  Date: 2020/11/18
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath() + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>历史成绩</title>
</head>
<body>
<p>${gradeList}</p>
</body>
</html>
