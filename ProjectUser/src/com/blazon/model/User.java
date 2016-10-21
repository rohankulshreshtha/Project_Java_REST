package com.blazon.model;
import java.util.Date;

public class User {
private String email_id,phone_no,name,status,password,role;
private Date cr_date,up_date,del_date;
public User(){}
public String getEmail_id() {
	return email_id;
}
public void setEmail_id(String email_id) {
	this.email_id = email_id;
}
public String getPhone_no() {
	return phone_no;
}
public void setPhone_no(String phone_no) {
	this.phone_no = phone_no;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Date getCr_date() {
	return cr_date;
}
public void setCr_date(Date cr_date) {
	this.cr_date = cr_date;
}
public Date getUp_date() {
	return up_date;
}
public void setUp_date(Date up_date) {
	this.up_date = up_date;
}
public Date getDel_date() {
	return del_date;
}
public void setDel_date(Date del_date) {
	this.del_date = del_date;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

}
//admin login page..
//admin page getuser.....
//admin update and delete user....