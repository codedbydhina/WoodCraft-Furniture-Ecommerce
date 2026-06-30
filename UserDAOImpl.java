package com.woodcraft.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.woodcraft.dao.UserDAO;
import com.woodcraft.model.User;
import com.woodcraft.util.DBconnection;

public class UserDAOImpl implements UserDAO {

	static Connection con = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	@Override
	public boolean registerUser(User user) {

		try {

			String query = "INSERT INTO users " + "(name,email,phone,password,role) " + "VALUES(?,?,?,?,?)";
			con = DBconnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPhone());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, "CUSTOMER");

			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (Exception e) {

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
	public User loginUser(String email, String password) {

		User user = null;

		try {

			String query = "SELECT * FROM users " + "WHERE email=? " + "AND password=?";
			con = DBconnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				user = new User();

				user.setUserId(rs.getInt("user_id"));

				user.setName(rs.getString("name"));

				user.setEmail(rs.getString("email"));

				user.setPhone(rs.getString("phone"));

				user.setRole(rs.getString("role"));
			}

		} catch (Exception e) {

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

		return user;
	}

	@Override
	public User getUserById(int userId) {

		User user = null;

		try {

			String query = "SELECT * FROM users " + "WHERE user_id=?";
			con = DBconnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, userId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				user = new User();

				user.setUserId(rs.getInt("user_id"));

				user.setName(rs.getString("name"));

				user.setEmail(rs.getString("email"));

				user.setPhone(rs.getString("phone"));
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

		return user;
	}

	@Override
	public boolean updateUser(User user) {

		try {

			String query = "UPDATE users " + "SET name=?, email=?, phone=? " + "WHERE user_id=?";

			con = DBconnection.getConnection();

			pstmt = con.prepareStatement(query);

			pstmt.setString(1, user.getName());

			pstmt.setString(2, user.getEmail());

			pstmt.setString(3, user.getPhone());

			pstmt.setInt(4, user.getUserId());

			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

}