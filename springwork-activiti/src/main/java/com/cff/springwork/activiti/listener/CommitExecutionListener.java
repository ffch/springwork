package com.cff.springwork.activiti.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

public class CommitExecutionListener implements ExecutionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6482750935517963649L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		String eventName = execution.getEventName();
		System.out.println("BeforCommitExecutionListener:"+eventName);
		
		
	}

}
