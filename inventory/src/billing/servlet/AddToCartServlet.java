package billing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import billing.model.Order;


@WebServlet("/AddToCart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
			int id = Integer.parseInt(request.getParameter("prodID"));
			
			ArrayList<Order> orderList = new ArrayList<>();
			Order orders = new Order();
			orders.setProduct_id(id);
			orders.setQuantity(1);
			HttpSession session = request.getSession();
			ArrayList<Order> order_list = (ArrayList<Order>) session.getAttribute("order-list");
			if(order_list == null) {
				orderList.add(orders);
				session.setAttribute("order-list", orderList);
				response.sendRedirect("index.jsp");
			} else {
				orderList = order_list;
				boolean exist = false;
				for(Order o:orderList) {
					if(o.getProduct_id() == id) {
						exist = true;
						out.print("<h3 style='color:crimson; text-align:center'>Product Already Exists in the Bill. <a href='index.jsp'>Go to Billing Page.</a></h3>");
					}
				}
				if(!exist) {
					orderList.add(orders);
					response.sendRedirect("index.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
