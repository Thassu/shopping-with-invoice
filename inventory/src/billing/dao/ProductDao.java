package billing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import billing.model.Order;
import billing.model.Product;

public class ProductDao {
	private Connection conn;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection conn) {
		this.conn = conn;
	}
	
	public Product getSingleProduct(int id) {
		Product prod = null;
		try {
			query = "SELECT * FROM products WHERE product_id=?";
			pst = this.conn.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				prod = new Product();
				prod.setProduct_id(rs.getInt("product_id"));
				prod.setName(rs.getString("product_name"));
				
				prod.setPrice(rs.getDouble("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prod;
	}
	
	public List<Order> getCartProducts(ArrayList<Order> orderList) {
		List<Order> products = new ArrayList<Order>();
		
		try {
			if(orderList.size() > 0) {
				for(Order item:orderList) {
					query = "SELECT * FROM products WHERE product_id=?";
					pst = this.conn.prepareStatement(query);
					pst.setInt(1, item.getProduct_id());
					rs = pst.executeQuery();
					while(rs.next()) {
						Order order = new Order();
						order.setProduct_id(rs.getInt("product_id"));
						order.setName(rs.getString("product_name"));
						
						order.setPrice(rs.getDouble("price"));
						order.setQuantity(item.getQuantity());
						products.add(order);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public double getTotalCartPrice(ArrayList<Order> orderList) {
		double sum = 0.0;
		try {
			if(orderList.size() > 0) {
				for(Order item:orderList) {
					query = "SELECT price FROM products WHERE product_id=?";
					pst.setInt(1,item.getProduct_id());
					rs = pst.executeQuery();
					while(rs.next()) {
						 sum += item.getQuantity()*rs.getDouble("price");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sum;
	}
}
