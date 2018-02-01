package com.cff.springwork.wallet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.cff.springwork.wallet.domain.ErrorCode;


@Transactional
public interface ErrorCodeDao extends CrudRepository<ErrorCode, Long>{
	List<ErrorCode> findByErrCode(String errCode);
}