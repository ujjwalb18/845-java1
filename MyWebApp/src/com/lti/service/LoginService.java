package com.lti.service;

import java.util.HashMap;

public class LoginService {
	private HashMap<String, String> users= new HashMap<String, String>();
	public LoginService()
	{
		users.put("majrul", "123");
		users.put("dinesh", "1234");
		users.put("Ujjwal", "433");
	}

	public boolean authenticate(String uname, String upass) {
		if(users.containsKey(uname))
				if(users.get(uname).contentEquals(upass))
					return true;
		return false;
	}

}
