<%@ page import="ma.shopping.model.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    user auth =(user) request.getSession().getAttribute("auth");
    if(auth != null){
    	request.setAttribute("auth", auth);
    	response.sendRedirect("index.jsp");
    }
    
    ArrayList<card> card_list = (ArrayList<card>) session.getAttribute("card-list");
	if(card_list != null){
		request.setAttribute("card_list",card_list);
	}
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title> 
<%@include file="include/head.jsp" %>
</head>
<body>

<%@include file="include/navbar.jsp" %>

<div class="container">
<div class="card w-15 mx-auto my-5">
<div class="card-header text-center">User Login</div>
<div class="card-body">

<form action="user-login" method="post">

<div class="form-group">
<label>Email Address</label>
<input type="text" class="form-control" name="login-email" placeholder="Enter yout Email" required>
</div>

<div class="form-group">
<label>Password</label>
<input type="password" class="form-control" name="login-password" placeholder="**********" required>
</div>

<div class="text-center">
<button type="submit" class="btn btn-pimary">login</button>

</div>
</form>

</div>
</div>
</div>

<%@include file="include/foot.jsp" %>
</body>
</html>