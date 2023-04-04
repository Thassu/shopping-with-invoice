<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Date" %>
<%@page import="java.text.DecimalFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="billing.model.Order" %>
<%@page import="billing.connection.*" %>
<%@page import="billing.dao.ProductDao" %>
<% 
DecimalFormat dcf = new DecimalFormat("##,###.##");
request.setAttribute("dcf", dcf);

SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
Date date = new Date();

@SuppressWarnings("unchecked")
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
<title>Invoice</title>
<%@include file="includes/header.jsp"%>
<style type="text/css">
@media print {
    #printbtn {
        display :  none;
    }
}

function removeAttributeSession() {
	session.removeAttribute("attributeName");
}
</style>
</head>
<body>
<div class="container mt-5">
		<div class="d-flex justify-content-center row">
			<div class="col-md-8">
				<div class="p-3 bg-white rounded">
					<div class="row">
						<div class="col-md-6">
							<h1 class="text-uppercase">Invoice</h1>
							<div class="billed">
								<span class="font-weight-bold">Invoice No. :  </span><%= request.getParameter("name").substring(0, 4).toUpperCase() %>/<%= (int)(Math.random()*(1000-100+1)+100) %><span
									class="ml-1"></span>
							</div>
							<div class="billed">
								<span class="font-weight-bold">Date : </span><%= date %><span
									class="ml-1"></span>
							</div>
							<div class="billed">
								<span class="font-weight-bold">Customer Name : </span><%= request.getParameter("name") %><span
									class="ml-1"></span>
							</div>
							<div class="billed">
								<span class="font-weight-bold">Phone No. : </span><%= request.getParameter("phone") %><span
									class="ml-1"></span>
							</div>
							
						</div>
						<div class="col-md-6 text-right mt-3">
							<h3 class="text-danger mb-0">Company Name</h3><br>
							<h4 class="text mb-0">Thasmiya Traders</h4>
						</div>
					</div>
					<div class="mt-3">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>Product</th>
										<th>Quantity</th>
										<th>Price</th>
										<th>Total</th>
									</tr>
								</thead>
								<%
									if (order_list != null) {
										for (Order o : orderProduct) {
									%>
									<tr>
										<td><%= o.getName() %></td>
										<td><%= o.getQuantity() %></td>
										<td>₹ <%= o.getPrice() %></td>
										<td>₹ <%= o.getPrice()*o.getQuantity() %></td>
									</tr>
									<%
										} 
									} 
									%>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="text-right mb-3">
						<div class="billed">
								<span class="font-weight-bold">Total Bill Amount : ₹ </span>${ (total>0)?dcf.format(total):0 }<span
									class="ml-1"></span>
							</div>
					</div>
					<hr>
					<div class="text-right mb-3">
						<a href="index.jsp"><button id="printbtn" class="btn btn-warning" type="submit" onClick="">Back</button></a>
						<button class="btn btn-success" id="printbtn" onClick="window.print();">Print</button>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@include file="includes/footer.jsp"%>
</body>
</html>