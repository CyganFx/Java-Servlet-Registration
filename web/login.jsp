<%--
  Created by IntelliJ IDEA.
  User: Думан
  Date: 11.09.2020
  Time: 1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="errorPage.jsp" %>
<html>
<head>
    <link rel="stylesheet" href="css/main.css">
    <title>Login Page</title>
</head>
<body>

<%-- Checks for existing session --%>

<% if (session.getAttribute("email") != null) {
    response.sendRedirect("welcome.jsp");
} %>

<%-- Checks id entered in URL --%>

<%
    try {
        if (!request.getParameter("id").equals(application.getInitParameter("correctId"))) {
            response.sendRedirect(request.getContextPath());
        }
    } catch (Exception e) {
        response.sendRedirect(request.getContextPath());
    }
%>

<!-- Login form -->

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <form method="post" action="login" class="login100-form validate-form">
					<span class="login100-form-title p-b-26">
						Welcome
					</span>

                <div class="wrap-input100 validate-input" data-validate="Valid email is: a@b.c">
                    <input class="input100" type="text" name="email">
                    <span class="focus-input100" data-placeholder="Email"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
                    <input class="input100" type="password" name="password">
                    <span class="focus-input100" data-placeholder="Password"></span>
                </div>

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn">
                            Login
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
