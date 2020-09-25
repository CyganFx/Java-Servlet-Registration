<%@ page import="Classes.User" %><%--
  Created by IntelliJ IDEA.
  User: Думан
  Date: 11.09.2020
  Time: 4:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="errorPage.jsp" %>
<html>
<head>
    <title>Welcome</title>
</head>
<style>
    body {
        font-family: "Segoe UI";
        background: antiquewhite;
    }
</style>
<body>

<%-- Preventing redirection without login --%>

<% if (session.getAttribute("email") == null) {
    response.sendRedirect(request.getContextPath() + "/id-check?id=" + application.getInitParameter("correctId"));
} %>

<%-- Using JSTL to get data from sessions --%>

<h1>Welcome ${first_name}!</h1>
<h2>There is a data we got from our database:</h2>
<ul>
    <li>User Id: ${id} </li>
    <li>Full Name: ${first_name} ${last_name}</li>
    <li>Job: ${job} </li>
</ul>
<p>Hey, we see that you are ${job}, checkout this page, it might be helpful for you:</p>
<form method="POST" action="website">
    <input type="submit" value="Click">
</form>

<h2>
    Hey, try our calculator, but be careful, it can cause an error!:
</h2>
<form method="POST" action="calculator">
    <input type="number" name="number1">
    <select name="operator">
        <option value="+">+</option>
        <option value="-">-</option>
        <option value="*">*</option>
        <option value="/">/</option>
    </select>
    <input type="number" name="number2"> </br> </br>
    <input type="submit" name="calculate" value="Calculate">
</form>


<h4>Website info:</h4>
<p>Servlet version: <%= getServletInfo() %>
</p>
<p>Servlet container used: <%= application.getServerInfo() %>
</p>

</body>
</html>
