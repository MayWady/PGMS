package com.pms.persistant.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ticketIdSearchService")
public class TicketIdSearchService {
	@Autowired
	SqlSession session;

	public String getId() {
		String s = session.selectOne("TicketMapper.getId");
		return s;
	}

	public String getStatusName(int id) {
		String s = session.selectOne("TicketMapper.getStatusName", id);
		return s;
	}

	public String getTicketTypeName(String id) {
		String s = session.selectOne("TicketMapper.getTicketTypeName", id);
		return s;
	}

	
}
