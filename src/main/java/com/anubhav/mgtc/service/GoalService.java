package com.anubhav.mgtc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anubhav.mgtc.dao.GoalDao;
import com.anubhav.mgtc.entity.Goal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class GoalService {
	@Autowired GoalDao goalDao;
	ObjectMapper mapper = new ObjectMapper();

	public JsonNode getGoalByMeetingId(String id) {
		ObjectNode response = mapper.createObjectNode();
		try {
			response.putPOJO("data", goalDao.getGoalByMeetingId(id));
			response.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("error", e.getMessage());
		}
		return response;
	}
	
	public JsonNode addorUpdateGoal(List<Goal> goals) {
		ObjectNode response = mapper.createObjectNode();
		try {
			goalDao.addorUpdateGoal((goals),null);
			response.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("error", e.getMessage());
		}
		return response;
	}

}
