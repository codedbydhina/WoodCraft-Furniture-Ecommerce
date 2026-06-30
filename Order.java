package com.woodcraft.model;

import java.sql.Timestamp;

public class Order {
	private int orderId;
	private int userId;
	private double totalAmount;
	private String orderStatus;
	private String shippingAddress;
	private Timestamp orderDate;

	private String customerName;
	private String customerEmail;

	public Order() {
		super();
	}

	public Order(int orderId, int userId, double totalAmount, String orderStatus, String shippingAddress,
			Timestamp orderDate, String customerName, String customerEmail) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		this.shippingAddress = shippingAddress;
		this.orderDate = orderDate;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

}
