package com.blazon.rest;
import java.net.UnknownHostException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.blazon.DAO.UserDao;
import com.blazon.model.User;

@Path("/saveuser")
public class AddUser {
	@POST
	@Path("/adduser")
	public String addUser(
			@QueryParam("email_id") String email_id,
			@QueryParam("phone_no") String phone_no,
			@QueryParam("name") String name,
			@QueryParam("password") String password) throws UnknownHostException{
		UserDao userdao=new UserDao();			
		User user=new User();
		             user.setPhone_no(phone_no);
		             user.setEmail_id(email_id);
		             user.setName(name);
					 user.setPassword(password);
					 user.setRole("non-admin");
					userdao.saveuser(user);
					return ("addUser is called, email_id : " + email_id + ", name : "+ name +", phone_no : " + phone_no);
	}
	
}