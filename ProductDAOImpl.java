package com.woodcraft.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woodcraft.dao.ProductDAO;
import com.woodcraft.model.Product;
import com.woodcraft.util.DBconnection;

public class ProductDAOImpl implements ProductDAO {
	static Connection con = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	@Override
	public boolean addProduct(Product product) {

		try {
			String query = "INSERT INTO products " + "(category_id, product_name," + " description, price,"
					+ " stock_quantity, image_path)" + " VALUES (?, ?, ?, ?, ?, ?)";
			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, product.getCategoryId());

			pstmt.setString(2, product.getProductName());

			pstmt.setString(3, product.getDescription());

			pstmt.setDouble(4, product.getPrice());

			pstmt.setInt(5, product.getStockQuantity());

			pstmt.setString(6, product.getImagePath());

			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Product getProductById(int productId) {

		Product product = null;

		try {

			String query = "SELECT * FROM products " + "WHERE product_id=?";
			con = DBconnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, productId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				product = new Product();

				product.setProductId(rs.getInt("product_id"));

				product.setCategoryId(rs.getInt("category_id"));

				product.setProductName(rs.getString("product_name"));

				product.setDescription(rs.getString("description"));

				product.setPrice(rs.getDouble("price"));

				product.setStockQuantity(rs.getInt("stock_quantity"));

				product.setImagePath(rs.getString("image_path"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return product;
	}

	@Override
	public List<Product> getAllProducts() {

		List<Product> products = new ArrayList<>();

		try {

			String query = "SELECT * FROM products";
			con = DBconnection.getConnection();
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				System.out.println("Product Found: " + rs.getString("product_name"));
				Product product = new Product();

				product.setProductId(rs.getInt("product_id"));

				product.setProductName(rs.getString("product_name"));

				product.setPrice(rs.getDouble("price"));

				product.setImagePath(rs.getString("image_path"));

				product.setStockQuantity(rs.getInt("stock_quantity"));

				products.add(product);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally

		{
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return products;
	}

	@Override
	public boolean updateProduct(Product product) {

		try {

			String query = "UPDATE products " + "SET product_name=?," + "price=? " + "WHERE product_id=?";
			con = DBconnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, product.getProductName());

			pstmt.setDouble(2, product.getPrice());

			pstmt.setInt(3, product.getProductId());

			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally

		{
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean deleteProduct(int productId) {

		try {

			con = DBconnection.getConnection();

			/* Delete from cart_items */

			String deleteCart = "DELETE FROM cart_items WHERE product_id=?";

			PreparedStatement ps1 = con.prepareStatement(deleteCart);

			ps1.setInt(1, productId);

			ps1.executeUpdate();

			/* Delete from order_items */

			String deleteOrder = "DELETE FROM order_items WHERE product_id=?";

			PreparedStatement ps2 = con.prepareStatement(deleteOrder);

			ps2.setInt(1, productId);

			ps2.executeUpdate();

			/* Delete product */

			String deleteProduct = "DELETE FROM products WHERE product_id=?";

			PreparedStatement ps3 = con.prepareStatement(deleteProduct);

			ps3.setInt(1, productId);

			int rows = ps3.executeUpdate();

			return rows > 0;

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (con != null)
					con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return false;
	}

	public int getStockQuantity(int productId) {

		int stock = 0;

		try {

			String query = "SELECT stock_quantity " + "FROM products " + "WHERE product_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, productId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				stock = rs.getInt("stock_quantity");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return stock;
	}

	public boolean reduceStock(int productId, int quantity) {

		try {

			String query = "UPDATE products " + "SET stock_quantity = stock_quantity - ? " + "WHERE product_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, quantity);

			pstmt.setInt(2, productId);

			return pstmt.executeUpdate() > 0;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Product> getRelatedProducts(int categoryId, int productId) {

		List<Product> products = new ArrayList<>();

		try {

			String query = "SELECT * FROM products " + "WHERE category_id=? " + "AND product_id != ? " + "LIMIT 4";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, categoryId);

			pstmt.setInt(2, productId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Product product = new Product();

				product.setProductId(rs.getInt("product_id"));

				product.setCategoryId(rs.getInt("category_id"));

				product.setProductName(rs.getString("product_name"));

				product.setPrice(rs.getDouble("price"));

				product.setImagePath(rs.getString("image_path"));

				product.setStockQuantity(rs.getInt("stock_quantity"));

				products.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return products;
	}

	@Override
	public List<Product> searchProducts(String keyword) {

		List<Product> products = new ArrayList<>();

		try {

			String query = "SELECT * FROM products " + "WHERE product_name LIKE ? " + "OR description LIKE ?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setString(1, "%" + keyword + "%");

			pstmt.setString(2, "%" + keyword + "%");

			rs = pstmt.executeQuery();

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

		} finally {

			try {

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return products;
	}

	@Override
	public List<Product> getProductsByRoom(int roomCategoryId) {

		List<Product> products = new ArrayList<>();

		try {

			String query = "SELECT * FROM products WHERE room_category_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, roomCategoryId);

			rs = pstmt.executeQuery();

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

		} finally {

			try {

				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return products;
	}

	@Override
	public List<Product> getLatestProducts(int limit) {

		List<Product> products = new ArrayList<>();

		try {

			String query = "SELECT * FROM products " + "ORDER BY created_date DESC " + "LIMIT ?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, limit);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Product product = new Product();

				product.setProductId(rs.getInt("product_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getDouble("price"));
				product.setImagePath(rs.getString("image_path"));

				products.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return products;
	}
}
