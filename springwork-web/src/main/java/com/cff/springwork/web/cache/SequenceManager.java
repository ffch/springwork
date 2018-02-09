package com.cff.springwork.web.cache;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.mybatis.mapper.AppSeqMapper;
import com.cff.springwork.network.common.DateUtil;

@Service
public class SequenceManager {
	private final static Logger log = LoggerFactory.getLogger(SequenceManager.class);
	@Autowired
	AppSeqMapper appSeqMapper;
	private ConcurrentLinkedQueue<Integer> transFlowSeq = new ConcurrentLinkedQueue<Integer>();
	private static int maxSeqSize = 10;
	private static int dengerSeqSize = 5;
	
	@PostConstruct
	public void init(){
		fillTransFlowSeq();
	}
	
	public String getReqFlow(){
		return DateUtil.formatNow8()+offerTransFlowSeq();
	}
	
	public int offerTransFlowSeq(){
		if(transFlowSeq.size()< dengerSeqSize){
			fillTransFlowSeq();
		}
		Integer el = transFlowSeq.poll();
		if(el == null){
			return offerTransFlowSeq();
		}
		return el;
	}
	
	public synchronized void fillTransFlowSeq(){
		int size = maxSeqSize - transFlowSeq.size();
		int para = appSeqMapper.getNextSeqWithSize("reqflowseq", size);
		log.info("获取到seq:" + para);
		for(int i=0;i<size;i++){
			transFlowSeq.add(para++);
		}
	}
	
}	
