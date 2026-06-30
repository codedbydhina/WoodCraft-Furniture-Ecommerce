package com.woodcraft.test;

import com.woodcraft.dao.ProductDAO;
import com.woodcraft.daoimpl.ProductDAOImpl;
import com.woodcraft.model.Product;

public class ProductDAOTest {

	public static void main(String[] args) {

		ProductDAO dao = new ProductDAOImpl();

		Product product = new Product();

		product.setCategoryId(1);

		product.setProductName("Luxury Wooden Chair");

		product.setDescription("Premium teak wood chair");

		product.setPrice(4999);

		product.setStockQuantity(10);

		product.setImagePath("images/chair.jpg");

		boolean result = dao.addProduct(product);

		if (result) {

			System.out.println("Product Added Successfully");
		} else {

			System.out.println("Failed");
		}
	}
}