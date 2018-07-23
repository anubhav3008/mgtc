package com.anubhav.mgtc.entity;

public class User {
	public User() {}
	public User(String id, String name, boolean isActive) {
		this.id = id;
		this.name = name;
		this.isActive = isActive;
	}
	
	private String id;
	private String name;
	private boolean isActive;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

}
