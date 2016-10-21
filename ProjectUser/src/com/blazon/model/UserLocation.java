package com.blazon.model;

public class UserLocation {
	private String user_id="";
	private String Title="";
    private String Snippet="";
    private String Latitude="";
    private String  Longitude="";
    public UserLocation(){}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getSnippet() {
		return Snippet;
	}
	public void setSnippet(String snippet) {
		Snippet = snippet;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}

