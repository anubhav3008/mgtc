package com.anubhav.mgtc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anubhav.mgtc.dao.MeetingDao;
import com.anubhav.mgtc.entity.Meeting;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MeetingService {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired MeetingDao meetingDao;
	public JsonNode getAllMeetings() {
		ObjectNode response = mapper.createObjectNode();
		try {
			response.putPOJO("data", meetingDao.getAllMeetings());
			response.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("error", e.getMessage());
		}
		return response;
	}
	
	public JsonNode getMeeting(int id) {
		ObjectNode response = mapper.createObjectNode();
		try {
			response.putPOJO("data", meetingDao.getMeeting(id));
			response.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("error", e.getMessage());
		}
		return response;
	}
	
	public JsonNode addOrUpdate(Meeting meeting) {
		ObjectNode response = mapper.createObjectNode();
		try {
			response.putPOJO("data", meetingDao.addOrUpdateMeeting(meeting));
			response.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("error", e.getMessage());
		}
		return response;
	}
}
