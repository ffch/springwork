package com.cff.springwork.web.endpoint.suggest;

import java.io.UnsupportedEncodingException;
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
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cff.springwork.activiti.service.SuggestService;
import com.cff.springwork.common.constant.Constant;
import com.cff.springwork.model.activi.UserTask;
import com.cff.springwork.web.cache.TaskTypeCache;

import net.sf.json.JSONObject;

@RestController("suggestController")
@RequestMapping("/suggest")
public class SuggestAction {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SuggestService suggestService;
	@Autowired
	private TaskTypeCache taskTypeCache;
	
	@RequestMapping(value="/createtask")
	public String createTask(@RequestBody UserTask userTask) throws Exception{
		String tmpTaskType = convertToType(userTask.getTasktype());
		userTask.setTasktype(tmpTaskType);

		suggestService.genTask(userTask);
		
		return Constant.RESPONSE_OK;
	}
	
	@RequestMapping(value="/applylist")
	public List<UserTask> applyList() throws Exception{
		List<UserTask> tasks = suggestService.applyList();
		for(int i =0 ;i < tasks.size();i++){
			UserTask tmp = tasks.get(i);
			tmp.setTasktype(convertToName(tmp.getTasktype()));
			tmp.setCurviewer(tmp.getCurviewer() == null ? "" : tmp.getCurviewer());
		}
		return tasks;
	}
	
	@RequestMapping(value="/waitlist")
	public List<UserTask> waitList() throws Exception{
		return suggestService.waitList();
	}
	
	@RequestMapping(value="/managelist")
	public List<UserTask> manageList() throws Exception{
		return suggestService.manageList();
	}
	
	@RequestMapping(value="/processcommit")
	public String processCommit(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String taskid = request.getParameter("taskid");
		String processid = request.getParameter("processid");
		logger.info("审批taskid：{},processid:{}",taskid,processid);
		try{
			suggestService.processCommit(taskid, processid);
		}catch(Exception e){
			return Constant.RESPONSE_FAIL;
		}
		return Constant.RESPONSE_OK;
	}

	public String convertToName(String type){
		return taskTypeCache.getTaskByType(type, "0").getName();
	}
	
	public String convertToType(String name){
		return taskTypeCache.getTaskByName(name, "0").getTasktype();
		
	}
}
