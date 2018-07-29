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

import com.anubhav.mgtc.entity.Goal;
import com.anubhav.mgtc.entity.mapper.GoalMapper;

@Service
public class GoalDao {
	@Autowired JdbiFactoryBean jdbiFactoryBean;
	private GoalJdbiDao getGoalJdbiDao() throws NoSuchExtensionException, Exception {
		return	jdbiFactoryBean.getObject().onDemand(GoalJdbiDao.class);	
	}
	public void addorUpdateGoal(List<Goal> goals) throws NoSuchExtensionException, Exception {
		CompletableFuture<Void> updates=  CompletableFuture.runAsync(()->  {
			try {
				getGoalJdbiDao().updateGoals(goals.stream().filter(goal->Objects.nonNull(goal.getId())).collect(Collectors.toList()));
			} catch (Exception e) {
				throw new CompletionException(e);
			}
		}); 
		CompletableFuture<Void> adds=  CompletableFuture.runAsync(()->{
			try {
				getGoalJdbiDao().addGoals(goals.stream().filter(goal->Objects.isNull(goal.getId())).collect(Collectors.toList()));
			} catch (Exception e) {
				throw new CompletionException(e);
			}
		});
		updates.get();
		adds.get();
	}
	
	public List<Goal> getGoalByMeetingId(int id) throws NoSuchExtensionException, Exception{
		return getGoalJdbiDao().getGoalByMeetingId(id);
	}
	
	
	interface GoalJdbiDao{
		
		@SqlBatch("insert  into goals(user_id, user_name, project_name, date, meeting_id) "
				+ "values (:getUserId,:getUserName,:getProjectName,:getDate,:getMeetingId)")
		public int[] addGoals(@BindMethods List<Goal> goals);
		
		@SqlBatch("insert  into goals(id, user_id, user_name, project_name, date, meeting_id) "
				+ "values (:getId,:getUserId,:getUserName,:getProjectName,:getDate,:getMeetingId) "
				+ "on conflict(id) do update set "
				+ "user_id=EXCLUDED.user_id,"
				+ "user_name=EXCLUDED.user_name,"
				+ "project_name=EXCLUDED.project_name,"
				+ "date=EXCLUDED.date,"
				+ "meeting_id=EXCLUDED.meeting_id")
		public int[] updateGoals(@BindMethods List<Goal> goals);
		
		@SqlQuery("select * from goals where meeting_id=:id")
		@RegisterRowMapper(GoalMapper.class)
		public List<Goal> getGoalByMeetingId(@Bind("id")int id);
		
		
	}

}
