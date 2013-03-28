package com.louishong.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfileWrapper {
    public static String sDriver = "org.sqlite.JDBC";
    public static String sUrl = "jdbc:sqlite:/Gravifile/ServerSide/Servers/db/Profiles.sqlite";
//    public static String sUrl = "jdbc:sqlite:C:\\OIC\\database\\Profiles.sqlite";
    public static SQliteBase sqlBase = new SQliteBase(sDriver, sUrl);

    private static ResultSet getDatabase() {
	return sqlBase.executeQuery("SELECT * FROM Profiles");
    }
    
    public static String getUserPoint(String name) {
	ResultSet results = getDatabase();

	String resultName;
	try {

	    while (results.next()) {
		resultName = results.getString("ChineseName");
		if (resultName.equals(name)) {
		    return results.getString("UserPoints");
		}
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    public static String getEmail(String name) {
	ResultSet results = getDatabase();

	String resultName;
	try {

	    while (results.next()) {
		resultName = results.getString("ChineseName");
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

    public static Boolean hasUser(String name) {
	ResultSet results = getDatabase();

	String resultName;
	try {

	    while (results.next()) {
		resultName = results.getString("ChineseName");
		if (resultName.equals(name)) {
		    return true;
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    public static String getUserJob(String name) {
	ResultSet results = getDatabase();

	String resultName;
	try {

	    while (results.next()) {
		resultName = results.getString("ChineseName");
		if (resultName.equals(name)) {

		    return results.getString("UserJob");
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static String getUserPhone(String name) {
	ResultSet results = getDatabase();

	String resultName;
	try {

	    while (results.next()) {
		resultName = results.getString("ChineseName");
		if (resultName.equals(name)) {

		    return results.getString("UserPhone");
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static ArrayList<String> getUserList() {
	
	
	return null;
	
    }
}
