package com.pms.persistant.service;

import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.persistant.dao.TicketRequestDao;

@Service("ticketDeleteService")
public class TicketDeleteService implements ServiceInterface<TicketRequestDao, Boolean> {
	@Autowired
	SqlSession sqlSession;

	@Override
	public Boolean doService(TicketRequestDao reqDao) throws SQLException {
		try {
			sqlSession.delete("TicketMapper.ticketDelete", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
