package com.woodcraft.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.woodcraft.dao.OrderItemDAO;
import com.woodcraft.model.OrderItem;
import com.woodcraft.util.DBconnection;

public class OrderItemDAOImpl implements OrderItemDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public boolean addOrderItem(OrderItem item) {

		try {

			String query = "INSERT INTO order_items(order_id,product_id,quantity,price) VALUES(?,?,?,?)";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, item.getOrderId());
			pstmt.setInt(2, item.getProductId());
			pstmt.setInt(3, item.getQuantity());
			pstmt.setDouble(4, item.getPrice());

			return pstmt.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(int orderId) {

		List<OrderItem> items = new ArrayList<>();

		try {

			String query = "SELECT oi.*, p.product_name, p.image_path " + "FROM order_items oi "
					+ "JOIN products p ON oi.product_id = p.product_id " + "WHERE oi.order_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, orderId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				OrderItem item = new OrderItem();

				item.setOrderItemId(rs.getInt("order_item_id"));

				item.setOrderId(rs.getInt("order_id"));

				item.setProductId(rs.getInt("product_id"));

				item.setQuantity(rs.getInt("quantity"));

				item.setPrice(rs.getDouble("price"));

				item.setProductName(rs.getString("product_name"));

				item.setImagePath(rs.getString("image_path"));

				items.add(item);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return items;
	}
}
