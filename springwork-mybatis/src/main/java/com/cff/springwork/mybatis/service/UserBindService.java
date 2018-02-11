package com.cff.springwork.mybatis.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cff.springwork.model.user.UserBind;
import com.cff.springwork.mybatis.mapper.UserBindMapper;

@Service
@Transactional
public class UserBindService {
	@Autowired
	UserBindMapper userBindMapper;

	public void save(UserBind userBind) {
		userBindMapper.save(userBind);
	}

	public UserBind findByUserNo(String userNo) {
		List<UserBind> list = userBindMapper.getUserBind(userNo);
		if(list == null || list.size() <1)return null;
		return list.get(0);
	}

	public boolean modify(UserBind user) {
		try {
			userBindMapper.modify(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
