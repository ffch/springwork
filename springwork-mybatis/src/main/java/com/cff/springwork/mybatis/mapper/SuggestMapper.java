package com.cff.springwork.mybatis.mapper;

import java.util.List;

import com.cff.springwork.model.activi.UserTask;

public interface SuggestMapper {

	public void save(UserTask userTask);
	
	public List<UserTask> getUserTask(String userid);
	
	public UserTask getUserTaskByTaskId(String taskid);
	
	public void updateStatus(UserTask userTask);
}
