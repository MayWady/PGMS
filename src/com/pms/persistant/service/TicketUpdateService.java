package com.pms.persistant.service;

import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.persistant.dao.TicketRequestDao;

@Service("ticketUpdateRegister")
public class TicketUpdateService implements ServiceInterface<TicketRequestDao, Boolean> {
	@Autowired
	SqlSession sqlSession;

	@Override
	public Boolean doService(TicketRequestDao reqDao) throws SQLException {
		try {
			sqlSession.update("TicketMapper.ticketUpdate", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
