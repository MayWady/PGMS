package com.pms.persistant.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("playthingIdSearchService")
public class PlaythingIdSearchService {

	@Autowired
	SqlSession session;

	public String getId() {
		String s = session.selectOne("PlaythingMapper.getId");
		return s;
	}
	
	public String getPCName(int id) {
		String s=session.selectOne("PlaythingMapper.getPCName",id);
		return s;
	}
	
	public String getStatusName(int id) {
		String s=session.selectOne("PlaythingMapper.getStatusName",id);
		return s;
	}

}
