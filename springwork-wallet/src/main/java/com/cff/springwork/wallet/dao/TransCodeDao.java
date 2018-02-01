package com.cff.springwork.wallet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.cff.springwork.wallet.domain.TransCode;


@CacheConfig(cacheNames = "transCode")
@Transactional
public interface TransCodeDao extends CrudRepository<TransCode, Long>{
	@Cacheable
	List<TransCode> findByTransCode(String TransCode);
}