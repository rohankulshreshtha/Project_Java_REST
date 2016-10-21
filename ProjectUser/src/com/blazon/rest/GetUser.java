package com.blazon.rest;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.blazon.DAO.UserDao;
import com.blazon.model.User;

@Path("/getuser")
public class GetUser {

	@GET
	  @Produces("application/json")
	  public List<User> getdefaultuser() throws UnknownHostException  {
		UserDao userdao=new UserDao();
		List<User> list=new ArrayList<User>();
//		String jsonInString1 = mapper.writeValueAsString(m);
//	String jsonInString2 = mapper.writeValueAsString(n);
		list=userdao.getalluser();

		//return Response.status(200).entity(list.get(0)+list.get(1)).build();
		return list;
	 
	 }  
	@Path("{f}")
	  @GET
	  @Produces("application/json")
	public Object getuserbyid(@PathParam("f") String  id) throws UnknownHostException{
		UserDao userdao=new UserDao();
		String MobilePattern = "[0-9]{10}";
		User user;
		if(id.matches(MobilePattern))
			user = userdao.getuserphone(id);
		else
			user = userdao.getuseremail(id);
		if(user==null)
			return "{\"error\":\"no user found\"}";
		else
		return user;
	}

}
