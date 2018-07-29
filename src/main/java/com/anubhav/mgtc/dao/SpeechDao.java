package com.anubhav.mgtc.dao;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import org.jdbi.v3.core.extension.NoSuchExtensionException;
import org.jdbi.v3.spring4.JdbiFactoryBean;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anubhav.mgtc.entity.Speech;
import com.anubhav.mgtc.entity.mapper.SpeechMapper;

@Service
public class SpeechDao {
	@Autowired JdbiFactoryBean jdbiFactoryBean;
	private SpeechJdbiDao getSpeechJdbiDao() throws NoSuchExtensionException, Exception {
		return	jdbiFactoryBean.getObject().onDemand(SpeechJdbiDao.class);	
	}
	
	public void addOrupdateSpeech(List<Speech> speeches) throws NoSuchExtensionException, Exception {
		CompletableFuture<Void> updates= CompletableFuture.runAsync(()->  {
			try {
				getSpeechJdbiDao().updateSpeeches(speeches.stream().filter(speech->Objects.nonNull(speech.getId())).collect(Collectors.toList()));
			}catch (Exception e) {
				throw new CompletionException(e);
			}
		});
		CompletableFuture<Void> adds=  CompletableFuture.runAsync(()->{
			try {
				getSpeechJdbiDao().addSpeeches(speeches.stream().filter(speech-> Objects.isNull(speech.getId())).collect(Collectors.toList()));
			} catch (Exception e) {
				throw new CompletionException(e);
			}
		});
		updates.get();
		adds.get();
	}
	
	public List<Speech> getSpeechByMeetingId(int MeetingId) throws NoSuchExtensionException, Exception{
		return getSpeechJdbiDao().getSpeechByMeetingId(MeetingId);
	}
	
	
	interface SpeechJdbiDao{
		@SqlQuery("select * from speeches where meeting_id = :id")
		@RegisterRowMapper(SpeechMapper.class)
		public List<Speech> getSpeechByMeetingId( @Bind("id")  int  id); 
		
		@SqlBatch("insert into speeches (id, meeting_id, project_name, speaker_name, speaker_id, evaluator_name, evaluator_id, time_min, time_max, date) " + 
				"values("
				+ ":getId,"
				+ ":getMeetingId,"
				+ ":getProjectName,"
				+ ":getSpeakerName,"
				+ ":getSpeakerId,"
				+ ":getEvaluatorName,"
				+ ":getEvaluatorId,"
				+ ":getTimeMin,"
				+ ":getTimeMax,"
				+ ":getDate)"
				+ " on conflict (id) "
				+ " do update set "
				+ " meeting_id=EXCLUDED.meeting_id,"
				+ " project_name=EXCLUDED.project_name,"
				+ " speaker_name=EXCLUDED.speaker_name,"
				+ " speaker_id=EXCLUDED.speaker_id,"
				+ " evaluator_name=EXCLUDED.evaluator_name,"
				+ " evaluator_id=EXCLUDED.evaluator_id,"
				+ " date=EXCLUDED.date,"
				+ " time_min=EXCLUDED.time_min,"
				+ " time_max=EXCLUDED.time_max"
				)
		public int[] updateSpeeches(@BindMethods List<Speech> speeches);
		

		@SqlBatch("insert into speeches (meeting_id, project_name, speaker_name, speaker_id, evaluator_name, evaluator_id, date, time_min, time_max) " + 
				"values("
				+ ":getMeetingId,"
				+ ":getProjectName,"
				+ ":getSpeakerName,"
				+ ":getSpeakerId,"
				+ ":getEvaluatorName,"
				+ ":getEvaluatorId,"
				+ ":getDate,"
				+ ":getTimeMin,"
				+ ":getTimeMax)")
		public int[] addSpeeches(@BindMethods List<Speech> speeches);
		
		
		
		
	}
}
