package com.woodcraft.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.woodcraft.dao.WishlistDAO;
import com.woodcraft.model.Product;
import com.woodcraft.util.DBconnection;

public class WishlistDAOImpl implements WishlistDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public boolean addToWishlist(int userId, int productId) {

		try {

			con = DBconnection.getConnection();

			String sql = "INSERT INTO wishlist(user_id, product_id) VALUES(?, ?)";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, userId);

			ps.setInt(2, productId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean removeFromWishlist(int userId, int productId) {

		try {

			con = DBconnection.getConnection();

			String sql = "DELETE FROM wishlist WHERE user_id=? AND product_id=?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, userId);

			ps.setInt(2, productId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean isInWishlist(int userId, int productId) {

		try {

			con = DBconnection.getConnection();

			String sql = "SELECT * FROM wishlist WHERE user_id=? AND product_id=?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, userId);

			ps.setInt(2, productId);

			ResultSet rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Product> getWishlistProducts(int userId) {

		List<Product> products = new ArrayList<>();

		try {

			con = DBconnection.getConnection();

			String sql = "SELECT p.* FROM products p " + "JOIN wishlist w ON p.product_id = w.product_id "
					+ "WHERE w.user_id = ?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Product product = new Product();

				product.setProductId(rs.getInt("product_id"));

				product.setCategoryId(rs.getInt("category_id"));

				product.setProductName(rs.getString("product_name"));

				product.setDescription(rs.getString("description"));

				product.setPrice(rs.getDouble("price"));

				product.setStockQuantity(rs.getInt("stock_quantity"));

				product.setImagePath(rs.getString("image_path"));

				products.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return products;
	}
}