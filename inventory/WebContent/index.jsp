<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.text.DecimalFormat" %>
<%@page import="billing.model.Order" %>
<%@page import="billing.connection.*" %>
<%@page import="billing.dao.ProductDao" %>
<% 
DecimalFormat dcf = new DecimalFormat("##,###.##");
request.setAttribute("dcf", dcf);

ArrayList<Order> order_list = (ArrayList<Order>) session.getAttribute("order-list");
List<Order> orderProduct = null;
if(order_list != null) {
	ProductDao prodDao = new ProductDao(DatabaseConnection.getConnection());
	orderProduct = prodDao.getCartProducts(order_list);
	double total = prodDao.getTotalCartPrice(order_list);
	request.setAttribute("order_list", order_list);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Billing</title>
<%@include file="includes/header.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
</head>
<body>
	<h1 class="text-center"> Shopping Spree </h1>
	<div class="container mt-5 mb-5">
		<h2>Product Details</h2>
		<form class="row g-3" method="get" action="AddToCart">
			<div class="col-auto">
				<input type="text" class="form-control" name="prodID"
					placeholder="Product ID">
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary mb-3">Add</button>
			</div>
		</form>


		<table class="table table-bordered mt-5 mb-5">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Product Name</th>
					<th scope="col">Price</th>
					<th scope="col">Quantity</th>
					<th scope="col">Total</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<% if(order_list != null) {
				for(Order o:orderProduct) { %>
					<tr>
					<td><%= o.getName() %></td>
					
					<td>₹ <%= o.getPrice() %></td>
					<td>
					<form action="order-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= o.getProduct_id()%>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<input type="text" name="quantity" class="form-control"  value="<%=o.getQuantity()%>" readonly> 
								<a class="btn btn-incre" href="QuantityIncreDecre?action=incre&id=<%=o.getProduct_id()%>"><i class="fas fa-plus-square"></i></a> 
								<a class="btn btn-decre" href="QuantityIncreDecre?action=decre&id=<%=o.getProduct_id()%>"><i class="fas fa-minus-square"></i></a>
							</div>
						</form>
					</td>
					<td>₹ <%= dcf.format(o.getQuantity()*o.getPrice()) %></td>
					<td>
					<a class="btn btn-danger" href="RemoveFromCart?id=<%= o.getProduct_id() %>">Delete</a>
					</td>
					</tr>
			<%	}
			}
			%>
			</tbody>
		</table>
		<h3>Total No. of Items : ${order_list.size()}</h3>
		<h3>Total Price : ₹ ${ (total>0)?dcf.format(total):0 }</h3>
		<hr class="solid mt-5 mb-5">
		<h2>Customer Details</h2>
		<form class="row g-3" method="get" action="invoice.jsp">
			<div class="col-auto">
				<input type="text" class="form-control" name="name"
					placeholder="Customer's Name">
			</div>
			<div class="col-auto">
				<input type="text" class="form-control" name="phone"
					placeholder="Customer's Phone">
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary">Invoice</button>
			</div>
		</form>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>