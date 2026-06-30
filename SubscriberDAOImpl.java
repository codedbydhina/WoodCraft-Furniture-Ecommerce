package com.woodcraft.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.woodcraft.dao.SubscriberDAO;
import com.woodcraft.util.DBconnection;

public class SubscriberDAOImpl implements SubscriberDAO {

	@Override
	public boolean subscribe(String email) {

		try {

			Connection con = DBconnection.getConnection();

			String query = "INSERT INTO subscribers(email) VALUES(?)";

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, email);

			return pstmt.executeUpdate() > 0;

		} catch (Exception e) {
		    System.out.println("Email already subscribed.");
		    return false;
		}

		
	}
}