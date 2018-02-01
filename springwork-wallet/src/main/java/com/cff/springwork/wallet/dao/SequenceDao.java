package com.cff.springwork.wallet.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cff.springwork.wallet.domain.Sequence;


@Transactional
public interface SequenceDao extends CrudRepository<Sequence, String>{
	
	Sequence findBySequenceName(String sequenceName);
	
	@Query(value = "select nextval_copy(?1,?2)",nativeQuery = true) 
	public int findEnoughBySequenceNameAndSize(@Param("seq_name") String seqName, @Param("size") int size);  
}