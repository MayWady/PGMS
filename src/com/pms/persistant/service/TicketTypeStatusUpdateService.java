package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.TicketTypeStatusRequestDao;

@Service("tickettypeStatusUpdateService")
public class TicketTypeStatusUpdateService implements ServiceInterface<TicketTypeStatusRequestDao, Boolean> {

	@Autowired
	SqlSession sqlSession;

	@Override
	public Boolean doService(TicketTypeStatusRequestDao reqDao) throws SQLException {
		try {
			sqlSession.update("TicketTypeStatusMapper.tickettypeStatusUpdate", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
