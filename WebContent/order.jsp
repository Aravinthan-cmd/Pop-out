<%@ page import="ma.shopping.model.*" %>
<%@ page import="ma.shopping.connection.*" %>
<%@ page import="ma.shopping.dao.OrderDao" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    
    user auth =(user) request.getSession().getAttribute("auth");
    List<order> orders = null;
    if(auth != null){
    	request.setAttribute("auth", auth);
    	OrderDao dao = new OrderDao(DBcon.getConnection());
    	orders =dao.userOrder(auth.getId());
    	
    }else{
    	response.sendRedirect("login.jsp");
    }
    
    ArrayList<card> card_list = (ArrayList<card>) session.getAttribute("card-list");
	if(card_list != null){
		request.setAttribute("card_list",card_list);
	}
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>order</title> 
<%@include file="include/head.jsp" %>
</head>
<body>
<%@include file="include/navbar.jsp" %>

<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			
			<%
			if(orders != null){
				for(order o:orders){%>
					<tr>
						<td><%=o.getDate() %></td>
						<td><%=o.getName() %></td>
						<td><%=o.getCategory() %></td>
						<td><%=o.getQuantity() %></td>
						<td><%=dcf.format(o.getPrice()) %></td>
						<td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrder_id() %>">Cancel Order</a></td>
					</tr>
				<%}
			}
			%>
			
			</tbody>
		</table>
	</div>

<%@include file="include/foot.jsp" %>
</body>
</html>