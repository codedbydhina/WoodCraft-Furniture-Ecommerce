package com.woodcraft.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.woodcraft.dao.OrderDAO;
import com.woodcraft.model.Order;
import com.woodcraft.util.DBconnection;

public class OrderDAOImpl implements OrderDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public int createOrder(Order order) {

		int orderId = 0;

		try {

			String query = "INSERT INTO orders(user_id,total_amount,order_status,shipping_address) VALUES(?,?,?,?)";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, order.getUserId());
			pstmt.setDouble(2, order.getTotalAmount());
			pstmt.setString(3, order.getOrderStatus());
			pstmt.setString(4, order.getShippingAddress());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {

				orderId = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return orderId;
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {

		List<Order> orders = new ArrayList<>();

		try {

			String query = "SELECT * FROM orders " + "WHERE user_id=? " + "ORDER BY order_date DESC";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Order order = new Order();

				order.setOrderId(rs.getInt("order_id"));

				order.setUserId(rs.getInt("user_id"));

				order.setTotalAmount(rs.getDouble("total_amount"));

				order.setOrderStatus(rs.getString("order_status"));

				order.setShippingAddress(rs.getString("shipping_address"));

				order.setOrderDate(rs.getTimestamp("order_date"));

				orders.add(order);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return orders;
	}

	@Override
	public List<Order> getAllOrders() {

		List<Order> orders = new ArrayList<>();

		try {

			String query = "SELECT o.*, u.name, u.email " + "FROM orders o " + "JOIN users u ON o.user_id = u.user_id "
					+ "ORDER BY o.order_date DESC";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Order order = new Order();

				order.setOrderId(rs.getInt("order_id"));

				order.setUserId(rs.getInt("user_id"));

				order.setTotalAmount(rs.getDouble("total_amount"));

				order.setOrderStatus(rs.getString("order_status"));

				order.setShippingAddress(rs.getString("shipping_address"));

				order.setOrderDate(rs.getTimestamp("order_date"));

				order.setCustomerName(rs.getString("name"));

				order.setCustomerEmail(rs.getString("email"));

				orders.add(order);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public boolean updateOrderStatus(int orderId, String status) {

		try {

			String query = "UPDATE orders " + "SET order_status=? " + "WHERE order_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setString(1, status);

			pstmt.setInt(2, orderId);

			return pstmt.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Order getOrderById(int orderId) {

		Order order = null;

		try {

			String query = "SELECT * FROM orders " + "WHERE order_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, orderId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				order = new Order();

				order.setOrderId(rs.getInt("order_id"));

				order.setUserId(rs.getInt("user_id"));

				order.setTotalAmount(rs.getDouble("total_amount"));

				order.setOrderStatus(rs.getString("order_status"));

				order.setShippingAddress(rs.getString("shipping_address"));

				order.setOrderDate(rs.getTimestamp("order_date"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return order;
	}

	@Override
	public boolean cancelOrder(int orderId) {

		try {

			String query = "UPDATE orders " + "SET order_status='CANCELLED' " + "WHERE order_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, orderId);

			return pstmt.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}
}
