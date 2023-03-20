<%@ page import="ma.shopping.connection.DBcon" %>
<%@ page import="ma.shopping.model.*" %>
<%@ page import="ma.shopping.dao.ProductDao" %>
<%@ page import="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    user auth =(user) request.getSession().getAttribute("auth");
    if(auth != null){
    	request.setAttribute("auth", auth);
    }
    
    ProductDao pd = new ProductDao(DBcon.getConnection());
    List<product> products = pd.getAllProducts();
    
    ArrayList<card> card_list = (ArrayList<card>) session.getAttribute("card-list");
	if(card_list != null){
		request.setAttribute("card_list",card_list);
	}
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title> 
<%@include file="include/head.jsp" %>
</head>
<body>
<%@include file="include/navbar.jsp" %>


<!-- -------------card for All products ------------------------ -->>
<div class="container">
<div class="card-header my-3">All products</div>
<div class="row">
<%
if(!products.isEmpty()){
	for(product p:products){  %>
		<div class="col-md-3 my-3">
		<div class="card" style="width: 18rem;">
		  <img class="card-img-top" src="product-images/<%=p.getImage() %>" alt="Card image cap">
		  <div class="card-body">
		    <h5 class="card-title"><%=p.getName() %></h5>
		    <h6 class="price"><%=p.getPrice() %></h6>
		    <h6 class="category"><%=p.getCategory() %></h6>
		    <div class="mt-3 d-flex justify-content-between">
		    <a href="add-to-cart?id=<%=p.getId() %>" class="btn btn-dark">Add to Cart </a>
		    <a href="order-now?quantity=1&id=<%=p.getId() %>" class="btn btn-primary">Buy Now</a>
		    </div>
		  </div>
		</div>
		</div>
	<%}
}
%>

</div>
</div>

<%@include file="include/foot.jsp" %>
</body>
</html>
