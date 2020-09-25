<%--
  Created by IntelliJ IDEA.
  User: Думан
  Date: 12.09.2020
  Time: 6:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: "Segoe UI";
            background: antiquewhite;
        }

        .error {
            color: red;
        }
    </style>
</head>
<body>
<h1>It's an error page</h1>
<h4>You've got an error, my friend: </h4>
<p class="error">
    <%= request.getAttribute("errorMessage") %>
</p>
You'd better fix it!
</body>
</html>
