package com.blazon.rest;

import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.blazon.DAO.UserDao;
import com.blazon.model.UserLocation;
@Path("/location")
public class GetUserLocation {

	@GET
	@Produces("application/json")
	@Path("/getuser")
	public List<UserLocation> getlocation(
			@QueryParam("radius") double radius,
			@QueryParam("center_lat") double center_lat,
			@QueryParam("centre_long") double centre_long
			) throws UnknownHostException{
		UserDao dao = new UserDao();
		//ArrayList<String> users=new ArrayList<String>();
		//List<User> userlist = new ArrayList<User>();
		return dao.getuser(radius, center_lat, centre_long);
}
}