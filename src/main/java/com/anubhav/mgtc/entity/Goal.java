package com.anubhav.mgtc.entity;

import java.sql.Date;

public class Goal {

	private int id;
	private String userId;
	private String userName;
	private String projectName;
	private Date date;
	private int meetingId;
	public int getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Goal(int id, String userId, String userName, String projectName, Date date, int meetingId) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.projectName = projectName;
		this.date = date;
		this.meetingId = meetingId;
	}
	public Goal() {
		
	}
	
}