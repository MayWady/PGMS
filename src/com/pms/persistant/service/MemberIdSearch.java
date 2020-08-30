package com.pms.persistant.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberIdSearch")
public class MemberIdSearch {
	@Autowired
	SqlSession session;

	public String getId() {
		String s = session.selectOne("MembershipMapper.getId");
		return s;
	}
	
	public String getStatus(String param) {
		String s = session.selectOne("MembershipMapper.getStatus",param);
		return s;
	}
	
	public String getType(String param) {
		String s = session.selectOne("MembershipMapper.getType",param);
		return s;
	}
}
