package com.blazon.DAO;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blazon.model.User;
import com.blazon.model.UserLocation;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
public class UserDao {
	
public List<User>  getalluser() throws UnknownHostException{
	
	DBCollection table = DataConnect.getCollection();
	DBCursor cursor=table.find();
	List<User> listOfuser = new ArrayList<User>();
	while(cursor.hasNext()){
		BasicDBObject basicdbobject=(BasicDBObject)cursor.next();
		User userobject=new User();
		userobject.setEmail_id(basicdbobject.getString("email_id"));
		userobject.setPhone_no(basicdbobject.getString("phone_no"));
		userobject.setName(basicdbobject.getString("name"));
		userobject.setPassword(basicdbobject.getString("password"));
		userobject.setRole(basicdbobject.getString("role"));
		listOfuser.add(userobject);
	}
	return listOfuser;
}
public User getuserphone(String phone_no) throws UnknownHostException{
	DBCollection table = DataConnect.getCollection();
	BasicDBObject  query=new BasicDBObject("phone_no",phone_no);
	DBCursor cursor=table.find(query);
	if(cursor.hasNext()){
		BasicDBObject basicdbobject=(BasicDBObject)cursor.next();
		User userobject=new User();
		userobject.setEmail_id(basicdbobject.getString("email_id"));
		userobject.setPhone_no(basicdbobject.getString("phone_no"));
		userobject.setName(basicdbobject.getString("name"));
		userobject.setPassword(basicdbobject.getString("password"));
		userobject.setRole(basicdbobject.getString("role"));
		return userobject;
	}
	else 
		return null;
}
public User getuseremail(String email_id) throws UnknownHostException{
	DBCollection table = DataConnect.getCollection();
	BasicDBObject  query=new BasicDBObject("email_id",email_id);
	DBCursor cursor=table.find(query);
	if(cursor.hasNext()){
		BasicDBObject basicdbobject=(BasicDBObject)cursor.next();
		User userobject=new User();
		userobject.setEmail_id(basicdbobject.getString("email_id"));
		userobject.setPhone_no(basicdbobject.getString("phone_no"));
		userobject.setName(basicdbobject.getString("name"));
		userobject.setPassword(basicdbobject.getString("password"));
		userobject.setRole(basicdbobject.getString("role"));
		return userobject;
	}
	else 
		return null;
}
public void saveuser(User user) throws UnknownHostException{
	DBCollection table = DataConnect.getCollection();
	BasicDBObject basicdbobject=new BasicDBObject("email_id",user.getEmail_id()).
											append("phone_no",user.getPhone_no()).
											append("name",user.getName()).
											append("password",user.getPassword()).
											append("role",user.getRole()).
											append("cr_date",new Date().getTime());
	table.insert(basicdbobject);
}
public void updateuser(User user){
	DBCollection table = DataConnect.getCollection();
	BasicDBObject searchQuery = new BasicDBObject();
	searchQuery.put("phone_no", user.getPhone_no());
	DBCursor cursor = table.find(searchQuery);
	BasicDBObject mongouser=(BasicDBObject)cursor.next();
	BasicDBObject query = new BasicDBObject();
	query.put("name", mongouser.get("name"));
	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("name",user.getName());
	newDocument.put("up_date", new Date().getTime());
	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);

	table.update(query, updateObj);
}
public List<UserLocation> getuser(double radius,double center_lat,double centre_long){
	
	double ltd = 0.0009*radius;
	double lgd = 0.001*radius;
	double cd = java.lang.Math.sqrt(ltd*ltd+lgd*lgd)/2;
	List<UserLocation> userlist = new ArrayList<UserLocation>();
	
		
		DBCollection table =DataConnect.getCollection() ;
		 BasicDBList[] a=new BasicDBList[10];
		 a[0]= new BasicDBList();
		 for(int i=1;i<=9;i++){
			 a[i]=new BasicDBList();
		 }
		 a[1].add(center_lat-ltd);		a[1].add(centre_long);
		 a[2].add(center_lat-cd);		a[2].add(centre_long-cd);
		 a[3].add(center_lat);			a[3].add(centre_long-lgd);
		 a[4].add(center_lat+cd);		a[4].add(centre_long-cd);
		 a[5].add(center_lat+ltd);		a[5].add(centre_long);
		 a[6].add(center_lat+cd);		a[6].add(centre_long+cd);
		 a[7].add(center_lat);			a[7].add(centre_long+lgd);
		 a[8].add(center_lat-cd);		a[8].add(centre_long+cd);
		 for(int i=1;i<9;i++)
			 a[0].add(a[i]);
		 a[0].add(a[1]);
		 a[9].add(a[0]);
		 
		 BasicDBObject query = new BasicDBObject("location",
				 			   new BasicDBObject("$geoWithin",
				 			   new BasicDBObject("$geometry",
								 new BasicDBObject("type",
										 "Polygon").
				 			   append("coordinates", a[9]))
			        )
			    ); 
		 DBCursor cursor =table.find(query);
		 DBObject c;
		 BasicDBList l;
		 while(cursor.hasNext()){
			 c=(DBObject) cursor.next().get("location");
		 l= (BasicDBList) c.get("coordinates");
		 UserLocation u = new UserLocation();
		 u.setLatitude(l.get(0).toString());
		 u.setLongitude(l.get(1).toString());
		 userlist.add(u);
		 }
		 	 c=null;
		 	 l=null;
		 return userlist;
}
} 
