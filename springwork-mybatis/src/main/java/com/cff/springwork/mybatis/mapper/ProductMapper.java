package com.cff.springwork.mybatis.mapper;

import java.util.List;

import com.cff.springwork.model.activi.ProductTask;

public interface ProductMapper {

	public void save(ProductTask userTask);
	
	public List<ProductTask> getUserTask(String userid);
	
	public ProductTask getUserTaskByTaskId(String taskid);
	
	public void updateStatus(ProductTask userTask);

	public List<ProductTask> getUserTaskByCurrentViwer(String userid);
}
