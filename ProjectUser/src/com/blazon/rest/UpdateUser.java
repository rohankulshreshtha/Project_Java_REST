package com.blazon.rest;


import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.blazon.DAO.UserDao;
import com.blazon.model.User;


@Path("/update")
public class UpdateUser {
	
	@POST
	@Path("/updateuser")
	public String updateuser(
			@QueryParam("email_id") String email_id,
			@QueryParam("phone_no") String phone_no,
			@QueryParam("name") String name
		) throws UnknownHostException, URISyntaxException {
		User user = new User();
		user.setEmail_id(email_id);
		user.setPhone_no(phone_no);
		user.setName(name);
		UserDao dao = new UserDao();
		dao.updateuser(user);
		return ("updateUser is called, email_id : " + email_id + ", name : "+ name +", phone_no : " + phone_no);
		
	}

}

