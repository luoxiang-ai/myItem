<%--
  Created by IntelliJ IDEA.
  User: luoxiang
  Date: 2020/11/12
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <meta charset="UTF-8">
    <title>菜单管理</title>
</head>
    <%-- 如果使用frameset的包含页面，主页不能有body --%>
    <frameset cols="250, *" border="1">
    <frame src="${pageContext.request.contextPath}/sys/toMenuLeft.action" name="left">
    <frame src="${pageContext.request.contextPath}/sys/toMenuRight.action" name="right">
    </frameset>
</html>
