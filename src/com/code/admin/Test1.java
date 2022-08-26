package com.code.admin;

import org.apache.commons.codec.digest.DigestUtils;

public class Test1 
{
	public static void main(String[] args) 
	{
		String password="admin";
		String newPassword = DigestUtils.md5Hex(password);
		System.out.println("Password is -- "+newPassword);
				
		
	}

}
