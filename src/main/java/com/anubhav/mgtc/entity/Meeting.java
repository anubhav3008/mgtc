package com.anubhav.mgtc.entity;

import java.sql.Date;

public class Meeting {
	
	private int id;
	private Date timing;
	private String ttmName;
	private String ttmId;
	private String grammarianName;
	private String grammarianId;
	private String ahCounterName;
	private String ahCounterId;
	private String tmodName;
	private String tmodId;
	private String timerName;
	private String timerId;
	private String geName;
	private String geId;
	private String theme;
	private String venue;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTiming() {
		return timing;
	}
	public void setTiming(Date timing) {
		this.timing = timing;
	}
	public String getTtmName() {
		return ttmName;
	}
	public void setTtmName(String ttmName) {
		this.ttmName = ttmName;
	}
	public String getTtmId() {
		return ttmId;
	}
	public void setTtmId(String ttmId) {
		this.ttmId = ttmId;
	}
	public String getGrammarianName() {
		return grammarianName;
	}
	public void setGrammarianName(String grammarianName) {
		this.grammarianName = grammarianName;
	}
	public String getGrammarianId() {
		return grammarianId;
	}
	public void setGrammarianId(String grammarianId) {
		this.grammarianId = grammarianId;
	}
	public String getAhCounterName() {
		return ahCounterName;
	}
	public void setAhCounterName(String ahCounterName) {
		this.ahCounterName = ahCounterName;
	}
	public String getAhCounterId() {
		return ahCounterId;
	}
	public void setAhCounterId(String ahCounterId) {
		this.ahCounterId = ahCounterId;
	}
	public String getTmodName() {
		return tmodName;
	}
	public void setTmodName(String tmodName) {
		this.tmodName = tmodName;
	}
	public String getTmodId() {
		return tmodId;
	}
	public void setTmodId(String tmodId) {
		this.tmodId = tmodId;
	}
	public String getTimerName() {
		return timerName;
	}
	public void setTimerName(String timerName) {
		this.timerName = timerName;
	}
	public String getTimerId() {
		return timerId;
	}
	public void setTimerId(String timerId) {
		this.timerId = timerId;
	}
	public String getGeName() {
		return geName;
	}
	public void setGeName(String geName) {
		this.geName = geName;
	}
	public String getGeId() {
		return geId;
	}
	public void setGeId(String geId) {
		this.geId = geId;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public Meeting(int id, Date timing, String ttmName, String ttmId, String grammarianName, String grammarianId,
			String ahCounterName, String ahCounterId, String tmodName, String tmodId, String timerName, String timerId,
			String geName, String geId, String theme, String venue) {
		this.id = id;
		this.timing = timing;
		this.ttmName = ttmName;
		this.ttmId = ttmId;
		this.grammarianName = grammarianName;
		this.grammarianId = grammarianId;
		this.ahCounterName = ahCounterName;
		this.ahCounterId = ahCounterId;
		this.tmodName = tmodName;
		this.tmodId = tmodId;
		this.timerName = timerName;
		this.timerId = timerId;
		this.geName = geName;
		this.geId = geId;
		this.theme = theme;
		this.venue = venue;
	}
	public Meeting() {}
	 
}
