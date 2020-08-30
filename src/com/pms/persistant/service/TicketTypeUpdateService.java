package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.TicketTypeRequestDao;

@Service("ticketTypeUpdateService")
public class TicketTypeUpdateService implements ServiceInterface<TicketTypeRequestDao, Boolean> {

	@Autowired
	SqlSession sqlSession;

	@Override
	public Boolean doService(TicketTypeRequestDao reqDao) throws SQLException {
		try {
			sqlSession.update("TicketTypeMapper.ticketTypeUpdate", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}