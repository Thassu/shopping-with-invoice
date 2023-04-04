package billing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billing.model.Order;


@WebServlet("/RemoveFromCart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if (id != null) {
				ArrayList<Order> order_list = (ArrayList<Order>) request.getSession().getAttribute("order-list");
				if(order_list != null) {
					for(Order c:order_list) {
						if(c.getProduct_id() == Integer.parseInt(id)) {
							order_list.remove(order_list.indexOf(c));
							break;
						}
					}
					response.sendRedirect("index.jsp");
				}
			} else {
				response.sendRedirect("index.jsp");
			}
		}
	}

}
