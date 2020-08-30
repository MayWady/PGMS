package com.pms.persistant.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userSearch")
public class UserSearch {
	@Autowired
	SqlSession session;

	public String getId() {
		String s = session.selectOne("UserMapper.getId");
		return s;
	}

}
