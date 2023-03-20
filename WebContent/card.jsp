<%@ page import="ma.shopping.model.*"%>
<%@ page import="java.util.*" %>
<%@ page import="ma.shopping.connection.*"%>
<%@ page import="ma.shopping.dao.*"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.text.DecimalFormat" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%

    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);

	user auth = (user) request.getSession().getAttribute("auth");
	if (auth != null) {
		request.setAttribute("auth", auth);
	}
	
	ArrayList<card> card_list = (ArrayList<card>) session.getAttribute("card-list");
	List<card> cart_product = null;
	
	if(card_list != null){
		ProductDao pdao = new ProductDao(DBcon.getConnection());
		cart_product = pdao.getCardProducts(card_list);
		double total = pdao.getTotalCartPrice(card_list);
		request.setAttribute("card_list",card_list);
		request.setAttribute("total", total);
		
		
	}
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>card</title>
<%@include file="include/head.jsp"%>

<style type="text/css">
.table tbody td{
vertical-align: middle;
}
.btn-incre, .btn-decre{
box-shadow: none;
font-size: 15px;
}
</style>

</head>
<body>
	<%@include file="include/navbar.jsp"%>

	<div class="container">

		<div class="d-flex py-3">
			<h3>Total price :$ ${(total>0)?dcf.format(total):0}</h3>
			<a class="mx-3 btn btn-primary" href="check-out">CheckOut</a>
			</div>
			<table class="table table-loght">
				<thead>
					<tr>
						<th scope="col">Name</th>
						<th scope="col">Category</th>
						<th scope="col">Price</th>
						<th scope="col">Quantity</th>
						<th scope="col">Cancel</th>
					</tr>
				</thead>
				<tbody>
				<% if(card_list != null){
					for(card c:cart_product){  %>
						
						<tr>
						<td><%=c.getName() %></td>
						<td><%=c.getCategory() %></td>
						<td><%=dcf.format(c.getPrice()) %></td>
						<td>
							<form action="order-now" method="post" class="form-inline">
								<input type="hidden" name="id" value="<%=c.getId() %>" class="form-input">
								<div class="form-group d-flex justify-content-between">
										
										<a class="btn btn-sm btn-decre"
										href="quantity-Inc-dec?action=dec&id=<%=c.getId() %>"> <i class="fas fa-minus-square"></i>
									</a>
										
										<input type="text" name="quantity" class="form-control"
										value="<%=c.getQuantity() %>" readonly>
										
										<a class="btn btn-sm btn-incre" href="quantity-Inc-dec?action=inc&id=<%=c.getId() %>"> <i
										class="fas fa-plus-square"></i></a> 
										
										<button type="submit" class="btn btn-primary">Buy Now</button>
								</div>
							</form>
						</td>
						<td> <a class="btn btn-sm btn-danger" href="remove-from-cart?id=<%=c.getId() %>">Remove</a> </td>
					</tr>
						
				<%	}
				}  %>
					
				</tbody>
			</table>
		</div>

	<%@include file="include/foot.jsp"%>
</body>
</html>