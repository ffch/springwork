package com.cff.springwork.web.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.cff.springwork.model.activi.TaskType;
import com.cff.springwork.mybatis.mapper.TaskTypeMapper;

public class TaskTypeCache {
	@Autowired
	TaskTypeMapper taskTypeMapper;
	
	@Cacheable(value="taskCache")
    public TaskType getTaskByType(String taskType,String type) {
		return taskTypeMapper.getTaskByType(taskType, type);
    }
	
	@Cacheable(value="taskCache")
    public TaskType getTaskByName(String name,String type) {
		return taskTypeMapper.getTaskByName(name, type);
    }
}
