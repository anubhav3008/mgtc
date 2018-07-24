package com.anubhav.mgtc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anubhav.mgtc.entity.Speech;
import com.anubhav.mgtc.service.SpeechService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/speeches")
public class SpeechController {
	@Autowired SpeechService speechService;
	
	@RequestMapping(path="/add", method=RequestMethod.POST)
	public @ResponseBody JsonNode addSpeech(@RequestBody Speech speech) {
		return speechService.addSpeech(speech);
	}
	
	@RequestMapping(path="/update", method=RequestMethod.PUT)
	public @ResponseBody JsonNode updateSpeech(@RequestBody Speech speech) {
		return speechService.updateSpeech(speech);
	}
	@RequestMapping(path="",method=RequestMethod.GET)
	public @ResponseBody JsonNode getSpeechByMeetingId(@RequestParam("meetingId")  int id) {
		return speechService.getSpeechByMeetingId(id);
	}


}
