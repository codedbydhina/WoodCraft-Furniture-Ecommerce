package com.woodcraft.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.woodcraft.dao.CategoryDAO;
import com.woodcraft.model.Product;
import com.woodcraft.util.DBconnection;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public List<Product> getProductsByCategory(int categoryId) {

		List<Product> products = new ArrayList<>();

		try {

			Connection con = DBconnection.getConnection();

			String query = "SELECT * FROM products WHERE category_id=?";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, categoryId);

			ResultSet rs = pstmt.executeQuery();

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