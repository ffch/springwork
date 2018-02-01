package com.cff.springwork.wallet.common;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.wallet.dao.SequenceDao;

@Service
public class SequenceManager {
	private final static Logger log = LoggerFactory.getLogger(SequenceManager.class);

	private ConcurrentLinkedQueue<Integer> transFlowSeq = new ConcurrentLinkedQueue<Integer>();
	private ConcurrentLinkedQueue<Integer> accNoSeq = new ConcurrentLinkedQueue<Integer>();
	private static int maxSeqSize = 10;
	private static int dengerSeqSize = 5;
	
	@PostConstruct
	public void init(){
		fillTransFlowSeq();
	}
	
	@Autowired
	SequenceDao sequenceDao;
	
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
		int para = sequenceDao.findEnoughBySequenceNameAndSize(Constant.TRAN_FLOW_SEQ, size);
		log.error("获取到seq:" + para);
		for(int i=0;i<size;i++){
			transFlowSeq.add(para++);
		}
	}
	
	public int offerAccNoSeq(){
		if(accNoSeq.size()< dengerSeqSize){
			fillAccNoSeq();
		}
		Integer el = accNoSeq.poll();
		if(el == null){
			return offerAccNoSeq();
		}
		return el;
	}
	
	public synchronized void fillAccNoSeq(){
		int size = maxSeqSize - accNoSeq.size();
		int para = sequenceDao.findEnoughBySequenceNameAndSize(Constant.ACC_NO_SEQ, size);
		log.error("获取到seq:" + para);
		for(int i=0;i<size;i++){
			accNoSeq.add(para++);
		}
	}
}	
