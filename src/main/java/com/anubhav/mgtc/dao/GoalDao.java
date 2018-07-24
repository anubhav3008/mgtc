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

import com.anubhav.mgtc.entity.Goal;
import com.anubhav.mgtc.entity.mapper.GoalMapper;

@Service
public class GoalDao {
	@Autowired JdbiFactoryBean jdbiFactoryBean;
	private GoalJdbiDao getGoalJdbiDao() throws NoSuchExtensionException, Exception {
		return	jdbiFactoryBean.getObject().onDemand(GoalJdbiDao.class);	
	}
	public int addGoal(Goal goal) throws NoSuchExtensionException, Exception {
		return getGoalJdbiDao().addGoal(goal);
	}
	
	public int updateGoal(Goal goal) throws NoSuchExtensionException, Exception {
		return getGoalJdbiDao().updateGoal(goal);
	}
	public List<Goal> getGoalByMeetingId(int id) throws NoSuchExtensionException, Exception{
		return getGoalJdbiDao().getGoalByMeetingId(id);
	}
	
	
	interface GoalJdbiDao{
		
		@SqlUpdate("insert  into goals(user_id, user_name, project_name, date, meeting_id) "
				+ "values (:getUserId,:getUserName,:getProjectName,:getDate,:getMeetingId)")
		public int addGoal(@BindMethods Goal goal);
		
		@SqlUpdate("insert  into goals(id, user_id, user_name, project_name, date, meeting_id) "
				+ "values (:getId,:getUserId,:getUserName,:getProjectName,:getDate,:getMeetingId) "
				+ "on conflict(id) do update set "
				+ "user_id=EXCLUDED.user_id,"
				+ "user_name=EXCLUDED.user_name,"
				+ "project_name=EXCLUDED.project_name,"
				+ "date=EXCLUDED.date,"
				+ "meeting_id=EXCLUDED.meeting_id")
		public int updateGoal(@BindMethods Goal goal);
		
		@SqlQuery("select * from goals where meeting_id=:id")
		@RegisterRowMapper(GoalMapper.class)
		public List<Goal> getGoalByMeetingId(@Bind("id")int id);
		
		
	}

}
