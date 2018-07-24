package com.anubhav.mgtc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anubhav.mgtc.entity.Goal;
import com.anubhav.mgtc.service.GoalService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/goals")
public class GoalController {
	
	@Autowired GoalService goalService;
	@RequestMapping(path="/add", method=RequestMethod.POST)
	public @ResponseBody JsonNode addGoal(@RequestBody Goal goal) {
		return goalService.addGoal(goal);
	}
	@RequestMapping(path="/update",method=RequestMethod.PUT)
	public @ResponseBody JsonNode update(@RequestBody Goal goal) {
		return goalService.updateGoal(goal);
	}
	@RequestMapping(path="", method=RequestMethod.GET)
	public @ResponseBody JsonNode getGoalByMeetingId(@RequestParam("meetingId") int id) {
		return goalService.getGoalByMeetingId(id);
	}

}
