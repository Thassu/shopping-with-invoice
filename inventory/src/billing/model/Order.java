package billing.model;

public class Order extends Product {
	private int order_id;
	private String customer_name;
	private String customer_phone;
	private int quantity;
	private String created_at;
	
	public Order() {
		super();
	}

	public Order(int order_id, String customer_name, String customer_phone, int quantity, String created_at) {
		super();
		this.order_id = order_id;
		this.customer_name = customer_name;
		this.customer_phone = customer_phone;
		this.quantity = quantity;
		this.created_at = created_at;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", customer_name=" + customer_name + ", customer_phone=" + customer_phone
				+ ", quantity=" + quantity + ", created_at=" + created_at + ", getProduct_id()=" + getProduct_id()
				+ ", getName()=" + getName() +  ", getPrice()=" + getPrice()
				+ "]";
	}
}