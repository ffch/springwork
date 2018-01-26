package com.cff.springwork.mybatis.mapper;

import com.cff.springwork.model.activi.TaskType;

public interface TaskTypeMapper {

	public TaskType getTaskByType(String taskType,String type);
	public TaskType getTaskByName(String name,String type);
}
