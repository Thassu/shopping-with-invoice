
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


@WebServlet("/QuantityIncreDecre")
public class QuantityIncreDecreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			
			request.getSession().getAttribute("order-list");
			ArrayList<Order> order_list = (ArrayList<Order>) request.getSession().getAttribute("order-list");
			if(action != null && id >= 1) {
				if(action.equals("incre")) {
					for(Order c:order_list) {
						if(c.getProduct_id() == id) {
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							response.sendRedirect("index.jsp");
						}
					}
				}
			}
			if(action != null && id >= 1) {
				if(action.equals("decre")) {
					for(Order c:order_list) {
						if(c.getProduct_id() == id && c.getQuantity() > 1) {
							int quantity = c.getQuantity();
							quantity--;
							c.setQuantity(quantity);
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