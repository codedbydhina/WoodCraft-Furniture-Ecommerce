package com.woodcraft.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.woodcraft.dao.CartDAO;
import com.woodcraft.model.CartItem;
import com.woodcraft.util.DBconnection;

public class CartDAOImpl implements CartDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public int getOrCreateCart(int userId) {

		try {

			con = DBconnection.getConnection();

			String checkQuery = "SELECT cart_id FROM cart WHERE user_id=?";

			pstmt = con.prepareStatement(checkQuery);

			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				return rs.getInt("cart_id");
			}

			String insertQuery = "INSERT INTO cart(user_id) VALUES(?)";

			pstmt = con.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, userId);

			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();

			if (rs.next()) {

				return rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public boolean addToCart(int cartId, int productId, int quantity) {

		try {

			con = DBconnection.getConnection();

			String checkItem = "SELECT * FROM cart_items " + "WHERE cart_id=? AND product_id=?";

			pstmt = con.prepareStatement(checkItem);

			pstmt.setInt(1, cartId);

			pstmt.setInt(2, productId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				int oldQty = rs.getInt("quantity");

				String updateQuery = "UPDATE cart_items " + "SET quantity=? " + "WHERE cart_id=? " + "AND product_id=?";

				pstmt = con.prepareStatement(updateQuery);

				pstmt.setInt(1, oldQty + quantity);

				pstmt.setInt(2, cartId);

				pstmt.setInt(3, productId);

				return pstmt.executeUpdate() > 0;
			}

			String insertQuery = "INSERT INTO cart_items" + "(cart_id,product_id,quantity)" + " VALUES(?,?,?)";

			pstmt = con.prepareStatement(insertQuery);

			pstmt.setInt(1, cartId);

			pstmt.setInt(2, productId);

			pstmt.setInt(3, quantity);

			return pstmt.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<CartItem> getCartItems(int cartId) {

		List<CartItem> items = new ArrayList<>();

		try {

			con = DBconnection.getConnection();

			String query = "SELECT ci.cart_item_id," + "ci.cart_id," + "ci.product_id," + "ci.quantity,"
					+ "p.product_name," + "p.price," + "p.image_path " + "FROM cart_items ci " + "JOIN products p "
					+ "ON ci.product_id = p.product_id " + "WHERE ci.cart_id=?";

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, cartId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				CartItem item = new CartItem();

				item.setCartItemId(rs.getInt("cart_item_id"));

				item.setCartId(rs.getInt("cart_id"));

				item.setProductId(rs.getInt("product_id"));

				item.setQuantity(rs.getInt("quantity"));

				item.setProductName(rs.getString("product_name"));

				item.setPrice(rs.getDouble("price"));

				item.setImagePath(rs.getString("image_path"));

				items.add(item);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return items;
	}

	@Override
	public int getCartItemCount(int cartId) {

		int count = 0;

		try {

			con = DBconnection.getConnection();

			String query = "SELECT SUM(quantity) total " + "FROM cart_items " + "WHERE cart_id=?";

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, cartId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				count = rs.getInt("total");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return count;
	}

	@Override
	public boolean updateCartQuantity(int cartItemId, int quantity) {

		try {

			String query =

					"UPDATE cart_items " + "SET quantity=? " + "WHERE cart_item_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, quantity);

			pstmt.setInt(2, cartItemId);

			return pstmt.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;
	}

	public int getExistingQuantity(int cartId, int productId) {

		int quantity = 0;

		try {

			String query =

					"SELECT quantity " + "FROM cart_items " + "WHERE cart_id=? " + "AND product_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, cartId);

			pstmt.setInt(2, productId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				quantity = rs.getInt("quantity");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return quantity;
	}

	public int getProductIdByCartItemId(int cartItemId) {

		int productId = 0;

		try {

			con = DBconnection.getConnection();

			String query = "SELECT product_id FROM cart_items WHERE cart_item_id=?";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, cartItemId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				productId = rs.getInt("product_id");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return productId;
	}

	public boolean removeCartItem(int cartItemId) {

		try {

			String query = "DELETE FROM cart_items WHERE cart_item_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, cartItemId);

			return pstmt.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	public boolean clearCart(int cartId) {

		try {

			String query = "DELETE FROM cart_items WHERE cart_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, cartId);

			return pstmt.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}
}