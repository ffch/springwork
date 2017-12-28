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

import net.sf.json.JSONObject;

@RestController("suggestController")
@RequestMapping("/suggest")
public class SuggestAction {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SuggestService suggestService;
	
	
	@RequestMapping(value="/createtask")
	public String createTask(@RequestBody UserTask userTask) throws Exception{
		suggestService.genTask(userTask);
		
		return Constant.RESPONSE_OK;
	}
	
	@RequestMapping(value="/applylist")
	public List<UserTask> applyList() throws Exception{
		return suggestService.applyList();
	}
	
	@RequestMapping(value="/waitlist")
	public List<UserTask> waitList() throws Exception{
		return suggestService.waitList();
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

	public String convertType(String type){
		if("基础服务".equals(type)){
			return "01";
		}
		if("技术支持".equals(type)){
			return "02";
		}
		if("产品申请".equals(type)){
			return "03";
		}
		if("会议申请".equals(type)){
			return "04";
		}
		else
			return "05";
		
	}
	
	public String convertToType(String name){
		if("01".equals(name)){
			return "基础服务";
		}
		if("02".equals(name)){
			return "技术支持";
		}
		if("03".equals(name)){
			return "产品申请";
		}
		if("04".equals(name)){
			return "会议申请";
		}
		else
			return "其他";
		
	}
}
