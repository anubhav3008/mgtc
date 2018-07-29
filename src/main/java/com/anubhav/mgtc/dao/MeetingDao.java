package com.anubhav.mgtc.dao;

import java.util.List;

import org.jdbi.v3.core.extension.NoSuchExtensionException;
import org.jdbi.v3.spring4.JdbiFactoryBean;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anubhav.mgtc.entity.Meeting;
import com.anubhav.mgtc.entity.mapper.MeetingMapper;

@Service
public class MeetingDao {
	@Autowired JdbiFactoryBean jdbiFactoryBean;
	private MeetingJdbiDao getMeetingJdbiDao() throws NoSuchExtensionException, Exception {
		return	jdbiFactoryBean.getObject().onDemand(MeetingJdbiDao.class);	
	}

	public int addOrUpdateMeeting(Meeting meeting) throws NoSuchExtensionException, Exception {
		MeetingJdbiDao meetingJdbiDao =  getMeetingJdbiDao();
		return meetingJdbiDao.addOrUpdateMeeting(meeting);
	}
	public List<Meeting> getAllMeetings() throws NoSuchExtensionException, Exception{
		MeetingJdbiDao meetingJdbiDao =  getMeetingJdbiDao();
		return meetingJdbiDao.getAllMeetings();
	}
	public Meeting getMeeting(int id) throws NoSuchExtensionException, Exception{
		MeetingJdbiDao meetingJdbiDao =  getMeetingJdbiDao();
		return meetingJdbiDao.getMeeting(id);
	}
	
	interface MeetingJdbiDao{

		@SqlQuery("select * from meetings")
		@RegisterRowMapper(MeetingMapper.class)
		public List<Meeting> getAllMeetings();

		@SqlQuery("select * from meetings where id= :id")
		@RegisterRowMapper(MeetingMapper.class)
		public Meeting getMeeting(@Bind("id") int id);

		@SqlUpdate("insert into meetings (id,timing,ttm_name,ttm_id,grammarian_name,grammarian_id,ah_counter_name,ah_counter_id,tmod_name,tmod_id,timer_name,timer_id,ge_name,ge_id,theme,venue)" + 
				"values ("
				+ ":getId,"
				+ ":getTiming,"
				+ ":getTtmName,"
				+ ":getTtmId,"
				+ ":getGrammarianName,"
				+ ":getGrammarianId,"
				+ ":getAhCounterName,"
				+ ":getAhCounterId,"
				+ ":getTmodName,"
				+ ":getTmodId,"
				+ ":getTimerName,"
				+ ":getTimerId,"
				+ ":getGeName,"
				+ ":getGeId,"
				+ ":getTheme,"
				+ ":getVenue)" + 
				"on CONFLICT(id) do update set "
				+ "ttm_name=EXCLUDED.ttm_name, "
				+ "ttm_id=EXCLUDED.ttm_id,"
				+ "grammarian_name=EXCLUDED.grammarian_name,"
				+ "grammarian_id=EXCLUDED.grammarian_id,"
				+ "ah_counter_name=EXCLUDED.ah_counter_name,"
				+ "ah_counter_id=EXCLUDED.ah_counter_id,"
				+ "tmod_name=EXCLUDED.tmod_name,"
				+ "tmod_id=EXCLUDED.tmod_id,"
				+ "timer_name=EXCLUDED.timer_name,"  
				+ "timer_id=EXCLUDED.timer_id,"
				+ "ge_name=EXCLUDED.ge_name,"
				+ "ge_id=EXCLUDED.ge_id,"
				+ "theme=EXCLUDED.theme,"
				+ "venue=EXCLUDED.venue, "
				+ "timing=EXCLUDED.timing;")
		public int addOrUpdateMeeting(@BindMethods Meeting meeting);
	}


}
