package com.louishong.database;

import java.util.*;

public class Testing {

    public static void main(String[] args) {
	System.out.println(ProfileWrapper.getUserList());
	Map<String, ArrayList<String>> userList = ProfileWrapper.getUserList();
	Iterator<String> userListKeys = userList.keySet().iterator();
	while (userListKeys.hasNext()) {
	    String key = userListKeys.next();
	    System.out.print(key + " :");
	    System.out.println(userList.get(key));
	}
	
    }
    
}