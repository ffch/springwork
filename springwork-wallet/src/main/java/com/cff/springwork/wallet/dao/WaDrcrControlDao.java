package com.cff.springwork.wallet.dao;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.cff.springwork.wallet.domain.WaDrcrControl;


@CacheConfig(cacheNames = "waDrcrControl")
@Transactional
public interface WaDrcrControlDao extends CrudRepository<WaDrcrControl, String>{
	@Cacheable
	WaDrcrControl findByTransCode(String transCode);
}