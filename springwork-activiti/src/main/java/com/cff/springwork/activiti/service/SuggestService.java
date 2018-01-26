package com.cff.springwork.activiti.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cff.springwork.common.user.UserInfoManager;
import com.cff.springwork.model.activi.UserTask;
import com.cff.springwork.model.security.AppUser;
import com.cff.springwork.mybatis.mapper.SuggestMapper;
import com.cff.springwork.mybatis.service.AppUserService;

@Service
public class SuggestService {
protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
  
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	SuggestMapper suggestMapper;
	
	@Autowired
	AppUserService appUserService;
	
	public void genTask(UserTask userTask){
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("productAdvice");
		Task tmp = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
		
		String userid = UserInfoManager.getInstance().getUserName();
		userTask.setTaskid(processInstance.getProcessInstanceId());
		userTask.setType("01");
		tmp.setAssignee(userid);
	    taskService.complete(tmp.getId());
	    
	    userTask.setUserid(userid);
	    suggestMapper.save(userTask);
	}
	
	public List<UserTask> applyList() throws Exception{
		String userid = UserInfoManager.getInstance().getUserName();
		List<UserTask> tasks = suggestMapper.getUserTask(userid);
		
		return tasks;
	}
	
	public List<UserTask> waitList() throws Exception{
		String userid = UserInfoManager.getInstance().getUserName();
		
		String  userType = findTaskType(userid);
		logger.info("准备查询userType为{}的任务",userType);
		System.out.println("准备查询userType为"+userType+"的任务");
		List<Task> tasks = taskService.createTaskQuery().taskName(userType).orderByTaskCreateTime().asc().list();
		List<UserTask> utasks = new ArrayList<UserTask>();
		for(int i=0;i<tasks.size(); i++){
			UserTask userTaskTmp = suggestMapper.getUserTaskByTaskId(tasks.get(i).getProcessInstanceId());
			if(userTaskTmp!=null){
				userTaskTmp.setRealTaskId(tasks.get(i).getId());
				utasks.add(userTaskTmp);
			}
		}
		
		return utasks;
	}
	
	public List<UserTask> manageList() {
		String userid = UserInfoManager.getInstance().getUserName();
		List<UserTask> utasks = suggestMapper.getUserTaskByCurrentViwer(userid);
			
		return utasks;
	}
	
	public synchronized Boolean processCommit(String taskid, String processid ) throws Exception{
		logger.info("审批taskid：{},processid:{}",taskid,processid);
		String userid = UserInfoManager.getInstance().getUserName();
		try{
			UserTask userTask = suggestMapper.getUserTaskByTaskId(processid);
			int tmpStatus = Integer.parseInt(userTask.getStatus());
			taskService.complete(taskid);
			userTask.setStatus((tmpStatus+1)+"");
			userTask.setCurviewer(userid);
			suggestMapper.updateStatus(userTask);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public String findTaskType(String userId){
		AppUser appUser = appUserService.findByName(userId);
		String userType = appUser.getUserType();
		if(userType == null)return null;
		if(!StringUtils.isEmpty(userType)){
			if("2001".equals(userType)){
				return "CustomerServiceApproval";
			}else if("0000".equals(userType)){
				return "ManagerApproval";
			}else{
				return "commit";
			}
		}
		return null;
	}
	
}
