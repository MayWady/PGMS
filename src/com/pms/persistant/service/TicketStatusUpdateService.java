package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.TicketStatusRequestDao;
import com.pms.persistant.dao.UserStatusRequestDao;

@Service("ticketStatusUpdateService")
public class TicketStatusUpdateService implements ServiceInterface<TicketStatusRequestDao,Boolean>{
	
	@Autowired
	SqlSession sqlSession;
	@Override
	public Boolean doService(TicketStatusRequestDao reqDao) throws SQLException {
		try {
			sqlSession.update("TicketStatusMapper.ticketStatusUpdate", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
