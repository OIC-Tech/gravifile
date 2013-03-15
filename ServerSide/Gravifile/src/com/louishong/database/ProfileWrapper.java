package com.louishong.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileWrapper {
	public static String sDriver = "org.sqlite.JDBC";
	public static String sUrl = "jdbc:sqlite:/Users/honglouis/Documents/ServerWorkspace/Gravifile/src/com/louishong/database/Profiles.sqlite";
	public static SQliteBase sqlBase = new SQliteBase(sDriver, sUrl);

	public static String getLastName(String name) {
		ResultSet results = sqlBase.executeQuery("SELECT * FROM Profiles");

		String resultName;
		try {

			while (results.next()) {
				resultName = results.getString("FirstName");
				if (resultName.equals(name)) {
					return results.getString("LastName");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getUserPoint(String name) {
		ResultSet results = sqlBase.executeQuery("SELECT * FROM Profiles");

		String resultName;
		try {

			while (results.next()) {
				resultName = results.getString("FirstName");
				if (resultName.equals(name)) {
					return results.getString("UserPoint");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getEmail(String name) {
		ResultSet results = sqlBase.executeQuery("SELECT * FROM Profiles");

		String resultName;
		try {

			while (results.next()) {
				resultName = results.getString("FirstName");
				if (resultName.equals(name)) {
					return results.getString("Email");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getGender(String name) {
		ResultSet results = sqlBase.executeQuery("SELECT * FROM Profiles");

		String resultName;
		try {

			while (results.next()) {
				resultName = results.getString("FirstName");
				if (resultName.equals(name)) {
					return results.getString("Gender");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getIsMember(String name) {
		ResultSet results = sqlBase.executeQuery("SELECT * FROM Profiles");

		String resultName;
		try {

			while (results.next()) {
				resultName = results.getString("FirstName");
				if (resultName.equals(name)) {
					
					return results.getString("IsMember");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean hasUser(String name) {
		ResultSet results = sqlBase.executeQuery("SELECT * FROM Profiles");

		String resultName;
		try {

			while (results.next()) {
				resultName = results.getString("FirstName");
				if (resultName.equals(name)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
