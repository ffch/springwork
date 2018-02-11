package com.cff.springwork.web.endpoint.product;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cff.springwork.activiti.service.ProductService;
import com.cff.springwork.common.constant.Constant;
import com.cff.springwork.common.user.UserInfoManager;
import com.cff.springwork.model.activi.ProductTask;
import com.cff.springwork.model.security.AppUser;
import com.cff.springwork.model.user.UserBind;
import com.cff.springwork.mybatis.service.UserBindService;
import com.cff.springwork.network.common.TransConstant;
import com.cff.springwork.network.tcp.data.TransactionMapData;
import com.cff.springwork.web.cache.TaskTypeCache;
import com.cff.springwork.web.entity.AccountInfo;
import com.cff.springwork.web.service.AccountOpenService;
import com.cff.springwork.web.service.AccountQueryService;
import com.cff.springwork.web.service.AccountRechargeService;
import com.cff.springwork.web.service.PublicService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController("productController")
@RequestMapping("/product")
public class ProductAction {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ProductService productService;
	@Autowired
	private TaskTypeCache taskTypeCache;
	@Autowired
	AccountOpenService accountOpenService;
	@Autowired
	AccountQueryService accountQueryService;
	@Autowired
	AccountRechargeService accountRechargeService;
	@Autowired
	PublicService publicService;
	@Autowired
	UserBindService userBindService;

	@RequestMapping(value = "/createtask")
	public String createTask(@RequestBody ProductTask userTask) throws Exception {
		String tmpTaskType = convertToType(userTask.getTasktype());
		System.out.println("TaskType:" + tmpTaskType);
		userTask.setTasktype(tmpTaskType);

		productService.genTask(userTask);

		return Constant.RESPONSE_OK;
	}

	@RequestMapping(value = "/applylist")
	public List<ProductTask> applyList() throws Exception {
		List<ProductTask> tasks = productService.applyList();
		for (int i = 0; i < tasks.size(); i++) {
			ProductTask tmp = tasks.get(i);
			System.out.println(tmp.toString());
			tmp.setTasktype(convertToName(tmp.getTasktype()));
			tmp.setCurviewer(tmp.getCurviewer() == null ? "" : tmp.getCurviewer());
		}
		return tasks;
	}

	@RequestMapping(value = "/waitlist")
	public List<ProductTask> waitList() throws Exception {
		return productService.waitList();
	}

	@RequestMapping(value = "/managelist")
	public List<ProductTask> manageList() throws Exception {
		return productService.manageList();
	}

	@RequestMapping(value = "/processcommit")
	public String processCommit(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String taskid = request.getParameter("taskid");
		String processid = request.getParameter("processid");
		logger.info("审批taskid：{},processid:{}", taskid, processid);
		try {
			productService.processCommit(taskid, processid);
		} catch (Exception e) {
			return Constant.RESPONSE_FAIL;
		}
		return Constant.RESPONSE_OK;
	}

	@RequestMapping(value = "/openAccount")
	public String openAccount(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		logger.info("申请开户：" + name + "-------" + passwd);
		String userNo = publicService.getUserInfo(request).getUserNo();
		if (userNo == null)
			return "1111";

		TransactionMapData tm = new TransactionMapData();
		tm.put(TransConstant.PASSWD, passwd);
		tm.put("SysMac", name);
		tm.put(TransConstant.USER_NO, userNo);
		String msg = "0000";

		try {
			TransactionMapData res = accountOpenService.trans(tm);
			if (!TransConstant.SUCCESS_CODE.equals(res.get("errCode").toString())) {
				msg = res.get("errMsg").toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "未知错误";
		}
		return msg;
	}

	@RequestMapping(value = "/queryAccount")
	public List<AccountInfo> queryAccount(HttpServletRequest request, HttpServletResponse response) {
		String userNo = publicService.getUserInfo(request).getUserNo();
		if (userNo == null)
			return null;

		UserBind userBind = userBindService.findByUserNo(userNo);

		if ("1".equals(userBind.getHasAccount())) {
			List<AccountInfo> lists = new ArrayList<AccountInfo>();
			return lists;
		}

		TransactionMapData tm = new TransactionMapData();
		tm.put("SysRemark", "queryAccount");
		tm.put(TransConstant.USER_NO, userNo);
		List<AccountInfo> lists = new ArrayList<AccountInfo>();
		try {
			TransactionMapData res = accountQueryService.trans(tm);
			if (!TransConstant.SUCCESS_CODE.equals(res.get("errCode").toString())) {
				return null;
			}
			logger.info("查询账户返回accDetails：{}", res.get("accDetails"));
			JSONArray ja = JSONArray.fromObject(res.get("accDetails"));
			logger.info("查询账户返JSONArray:{}", ja);
			for (int js = 0; js < ja.size(); js++) {
				JSONObject job = ja.getJSONObject(js);
				AccountInfo accountInfo = new AccountInfo();
				accountInfo.setAccNo(job.get("accNo") == null ? "" : job.get("accNo").toString());
				accountInfo.setAccName(job.get("accType") == null ? ""
						: (job.get("accType").toString().equals("1001") ? "主账户" : "其他"));
				accountInfo.setBal(job.get("bal") == null ? "" : job.get("bal").toString());
				accountInfo.setFrzBal(job.get("frzBal") == null ? "" : job.get("frzBal").toString());
				accountInfo.setLoanBal(job.get("loanBal") == null ? "" : job.get("loanBal").toString());
				lists.add(accountInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return lists;
	}
	
	@RequestMapping(value = "/rechargeAccount")
	public String rechargeAccount(HttpServletRequest request, HttpServletResponse response) {
		String userNo = publicService.getUserInfo(request).getUserNo();
		if (userNo == null)
			return "您还没有登录";
		String amt = request.getParameter("amt");
		String pwd = request.getParameter("pwd");
		UserBind userBind = userBindService.findByUserNo(userNo);

		if ("1".equals(userBind.getHasAccount())) {
			return "您还没有开通钱包";
		}
		String msg = "充值成功";
		TransactionMapData tm = new TransactionMapData();
		tm.put("SysRemark", "recharge");
		tm.put(TransConstant.ACC_NO, userBind.getAccNo());
		tm.put("passwdJudge", TransConstant.ACCOUNT_PASSWD_NOTJUDGE);
		tm.put("transAmt", amt);
		tm.put("passwd", pwd);
		try {
			TransactionMapData res = accountRechargeService.trans(tm);
			if (!TransConstant.SUCCESS_CODE.equals(res.get("errCode").toString())) {
				msg = res.get("errMsg").toString();
				return msg;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg = "未知错误";
		}
		return msg;
	}

	public String convertToName(String type) {
		return taskTypeCache.getTaskByType(type, "1").getName();
	}

	public String convertToType(String name) {
		return taskTypeCache.getTaskByName(name, "1").getTasktype();

	}
}
