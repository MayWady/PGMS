package com.pms.persistant.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ticketTypeIdSearchService")
public class TicketTypeIdSearchService {

	@Autowired
	SqlSession session;

	public String getId() {
		String s = session.selectOne("TicketTypeMapper.getId");
		return s;
	}

	public String getPlaythingId(String s) {
		String ans = session.selectOne("TicketTypeMapper.getPlaything", s);
		return ans;
	}

	public String getStatusName(int id) {
		String s = session.selectOne("TicketTypeMapper.getStatusName", id);
		return s;
	}

}
