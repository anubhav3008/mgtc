package com.anubhav.mgtc.service;

import com.anubhav.mgtc.aggregator.LeaderBoardAggregator;
import com.anubhav.mgtc.dao.MeetingDao;
import com.anubhav.mgtc.dao.SpeechDao;
import com.anubhav.mgtc.entity.Meeting;
import com.anubhav.mgtc.entity.Speech;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBoardService {
    ObjectMapper mapper = new ObjectMapper();
    @Autowired  MeetingDao meetingDao;
    @Autowired  SpeechDao speechDao;

    @Autowired
    LeaderBoardAggregator leaderBoardAggregator;
    public JsonNode getLeaderBoardByName()  {

        ObjectNode response = mapper.createObjectNode();
        try {
            List<Meeting> meetings =  meetingDao.getAllMeetings();
            List<Speech> speeches =  speechDao.getAllSpeeches();
            response.putPOJO("data",leaderBoardAggregator.aggregatorMeetingAndSpeech(meetings,speeches) );
            response.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }
}
