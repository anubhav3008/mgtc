package com.anubhav.mgtc.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.jdbi.v3.core.extension.NoSuchExtensionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anubhav.mgtc.entity.Goal;
import com.anubhav.mgtc.entity.Meeting;
import com.anubhav.mgtc.entity.Speech;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Service
public class  MeetingJsonDao {

	@Autowired SpeechDao speechDao;
	@Autowired MeetingDao meetingDao;
	@Autowired GoalDao goalDao;
	ObjectMapper mapper = new ObjectMapper();


	public JsonNode getMeetingJson(int meetingId) throws NoSuchExtensionException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode meetingJson=mapper.createObjectNode();
		CompletableFuture<Void> meetingFuture=  CompletableFuture.runAsync(()->  {
			try {
				meetingJson.putPOJO("meeting", meetingDao.getMeeting(meetingId));
			} catch (Exception e) {
				throw new CompletionException(e);
			}
		});
		CompletableFuture<Void> speechFuture=  CompletableFuture.runAsync(()->  {
			try {
				meetingJson.putPOJO("speech", speechDao.getSpeechByMeetingId(meetingId));
			} catch (Exception e) {
				throw new CompletionException(e);
			}
		});
		CompletableFuture<Void> goalFuture=  CompletableFuture.runAsync(()->  {
			try {
				meetingJson.putPOJO("goal", goalDao.getGoalByMeetingId(meetingId));
			} catch (Exception e) {
				throw new CompletionException(e);
			}
		});
		meetingFuture.get();
		speechFuture.get();
		goalFuture.get();
		return meetingJson;
	}

	public void addMeetingJSON(JsonNode meetingJson) throws NoSuchExtensionException, Exception {
		CompletableFuture<Void> meeting=  CompletableFuture.runAsync(()->{
			try {
				addMeeting(meetingJson);
			} catch (Exception e) {
				throw new CompletionException(e);
			}
		});
		CompletableFuture<Void> speeches =  CompletableFuture.runAsync(()->{
			try {
				addOrupdateSpeeches(meetingJson);
			} catch (Exception e) {
				throw new CompletionException(e);
			}
		});
		CompletableFuture<Void> goals=  CompletableFuture.runAsync(()->{
			try {
				addOrupdateGoals(meetingJson);
			} catch (Exception e) {
				throw new CompletionException(e);
			}
		});

		meeting.get();
		speeches.get();
		goals.get();
	}
	private void addMeeting(JsonNode meetingJson) throws NoSuchExtensionException, Exception {

		JsonNode meeting = meetingJson.get("meeting");
		if(Objects.nonNull(meeting)) {
			Meeting meetingDO= mapper.treeToValue(meeting, Meeting.class);	
			meetingDao.addOrUpdateMeeting(meetingDO);

		}
	}

	private void addOrupdateSpeeches(JsonNode meetingJson) throws NoSuchExtensionException, Exception {
		JsonNode speech = meetingJson.get("speech");
		if(Objects.nonNull(speech)) {
			List<Speech> speechDOs= Arrays.asList(mapper.treeToValue(speech, Speech[].class));
			speechDao.addOrupdateSpeech(speechDOs);
		}

	}

	private void addOrupdateGoals(JsonNode meetingJson) throws NoSuchExtensionException, Exception {
		JsonNode goal = meetingJson.get("goal");


		if(Objects.nonNull(goal)) {
			List<Goal> goalDOs= Arrays.asList( mapper.treeToValue(goal, Goal[].class));
			goalDao.addorUpdateGoal(goalDOs);
		}


	}
}
