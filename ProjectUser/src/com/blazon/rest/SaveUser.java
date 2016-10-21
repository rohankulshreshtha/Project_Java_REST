package com.blazon.rest;

import java.net.UnknownHostException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.blazon.model.User;
import com.blazon.DAO.UserDao;

@Path("/save")
public class SaveUser {
	@POST
	@Path("/adduser")
	public String addUser(
			@FormParam("email_id") String email_id,
			@FormParam("phone_no") String phone_no,
			@FormParam("name") String name,
			@FormParam("password") String password,
			@FormParam("confirm_password") String confirm_password) throws UnknownHostException{
		UserDao userdao=new UserDao();			
		User user=new User();
		             user.setPhone_no(phone_no);
		             user.setEmail_id(email_id);
		             user.setName(name);
					 user.setPassword(password);
					userdao.saveuser(user);
					return ("addUser is called, email_id : " + email_id + ", name : "+ name +", phone_no : " + phone_no);
	}
	
}
