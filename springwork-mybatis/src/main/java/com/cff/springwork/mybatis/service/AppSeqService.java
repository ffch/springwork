package com.cff.springwork.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.common.constant.Constant;
import com.cff.springwork.mybatis.mapper.AppSeqMapper;

@Service
public class AppSeqService {
	@Autowired
	AppSeqMapper appSeqMapper;
	
	public String nextSeq(String seqName){
		int userSeq = appSeqMapper.getNextSeq(seqName);
		String userNo = Constant.USERTYPE + Constant.COMMONAEAR + String.format("%06d", userSeq);
		return userNo;
	}
}
