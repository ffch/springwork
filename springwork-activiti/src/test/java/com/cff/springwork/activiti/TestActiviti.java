//package com.cff.springwork.activiti;
//
//
//import static org.junit.Assert.*;
//
//import java.util.List;
//
//import org.activiti.engine.HistoryService;
//import org.activiti.engine.ManagementService;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.history.HistoricTaskInstance;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.activiti.engine.task.TaskInfo;
//import org.activiti.engine.test.ActivitiRule;
//import org.activiti.engine.test.Deployment;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring-test.xml")
//public class TestActiviti {
//
//  @Autowired
//  private RuntimeService runtimeService;
//
//  @Autowired
//  private TaskService taskService;
//  
//  @Autowired
//  private RepositoryService repositoryService;
//  
//  @Autowired
//  private HistoryService historyService;
//  
//  ManagementService managementService;
//
//  //@Test
//  //@Deployment
//  public void simpleProcessTest() {  
//	ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("productAdvice");
//	System.out.println(processInstance.getActivityId()+"--====" + processInstance.getProcessDefinitionId()+ "=====" + processInstance.getId() +"====="+ processInstance.getProcessDefinitionKey() + "=====" + processInstance.getProcessDefinitionName() + "=====" + processInstance.getProcessInstanceId());
//	System.out.println(processInstance.getTenantId() + "+++++++" + processInstance.getProcessInstanceId());
//	List<Task> tasks = taskService.createTaskQuery().list();
//    for(int i=0;i<tasks.size();i++)
//    {
//    	Task tmp = tasks.get(i);
//        System.out.println(tmp.getName() + "-----" +tmp.getParentTaskId() + "----"+tmp.getExecutionId()+ "---" + tmp.getAssignee() + "----" + tmp.getId() + "----" + tmp.getProcessDefinitionId()+ "----" + tmp.getDelegationState()+"----" + tmp.getDescription());
//        taskService.complete(tasks.get(i).getId());
//
//    }
//    System.out.println(runtimeService.createProcessInstanceQuery().list());
//
//  }
//  
// // @Test
//  public void queryTest() {  
//    //runtimeService.startProcessInstanceByKey("productAdvice");
//	String assignee = "Lily";
//    List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).orderByTaskCreateTime().asc().list();
//    for(int i=0;i<tasks.size();i++)
//    {
//    	Task tmp = tasks.get(i);
//        System.out.println(tmp.getName() + "---" + tmp.getAssignee() + "----" + tmp.getOwner() + "----" + tmp.getProcessDefinitionId()+ "----" + tmp.getDelegationState()+"----" + tmp.getDescription());
//        
//    }
//
//   // taskService.complete(tasks.get(0).getId());
//  }
//  
//  //@Test
// // @Deployment
//  public void singleProcessTest() {  
//	ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("productAdvice");
//	System.out.println(processInstance.getActivityId()+"--====" + processInstance.getProcessDefinitionId()+ "=====" + processInstance.getId() +"====="+ processInstance.getProcessDefinitionKey() + "=====" + processInstance.getProcessDefinitionName() + "=====" + processInstance.getProcessInstanceId());
//	System.out.println(processInstance.getTenantId() + "+++++++" + processInstance.getProcessInstanceId());
//	Task tmp = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
//    
//    System.out.println(tmp.getName() + "-----" +tmp.getParentTaskId() + "----"+tmp.getExecutionId()+ "---" + tmp.getAssignee() + "----" + tmp.getId() + "----" + tmp.getProcessDefinitionId()+ "----" + tmp.getDelegationState()+"----" + tmp.getDescription());
//    tmp.setAssignee("15607110725");
//    taskService.complete(tmp.getId());
//    System.out.println(tmp.getName() + "-----" +tmp.getParentTaskId() + "----"+tmp.getExecutionId()+ "---" + tmp.getAssignee() + "----" + tmp.getId() + "----" + tmp.getProcessDefinitionId()+ "----" + tmp.getDelegationState()+"----" + tmp.getDescription());
//
//  }
//  
//  //@Test
//  public void hisQuery(){
//		List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery().list();
//		for(int i=0;i<tasks.size();i++)
//	    {
//			HistoricTaskInstance tmp = tasks.get(i);
//	        System.out.println(tmp.getAssignee()+"---" + tmp.getId() + "---" + tmp.getName()
//	        		+ "---" + tmp.getParentTaskId() + "---" + tmp.getProcessDefinitionId()
//	        		+"----" + tmp.getProcessInstanceId() + "----" + tmp.getCreateTime());   
//	    }
//  }
//  
////@Test
// public void queryGroupTest() {  
//   //runtimeService.startProcessInstanceByKey("productAdvice");
//	String assignee = "ManagerApproval";
//   List<Task> tasks = taskService.createTaskQuery().taskName(assignee).orderByTaskCreateTime().asc().list();
//   for(int i=0;i<tasks.size();i++)
//   {
//   	Task tmp = tasks.get(i);
//       System.out.println(tmp.getName() + "---" + tmp.getAssignee() + "----" + tmp.getOwner() + "----" + tmp.getProcessDefinitionId()+ "----" + tmp.getDelegationState()+"----" + tmp.getDescription());
//       
//   }
//
//  // taskService.complete(tasks.get(0).getId());
// }
//}
