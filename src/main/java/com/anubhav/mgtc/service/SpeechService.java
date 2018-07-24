package com.anubhav.mgtc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anubhav.mgtc.dao.SpeechDao;
import com.anubhav.mgtc.entity.Speech;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Service
public class SpeechService {
	
	@Autowired SpeechDao speechDao;
	ObjectMapper mapper = new ObjectMapper();

	public JsonNode addSpeech(Speech speech) {
		ObjectNode response = mapper.createObjectNode();
		try {
			response.putPOJO("data", speechDao.addSpeech(speech));
			response.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("error", e.getMessage());
		}
		return response;
	}
	public JsonNode updateSpeech(Speech speech) {
		ObjectNode response = mapper.createObjectNode();
		try {
			response.putPOJO("data", speechDao.updateSpeech(speech));
			response.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("error", e.getMessage());
		}
		return response;
	}
	public JsonNode getSpeechByMeetingId(int id) {
		ObjectNode response = mapper.createObjectNode();
		try {
			response.putPOJO("data", speechDao.getSpeechByMeetingId(id));
			response.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("error", e.getMessage());
		}
		return response;
	}

}
