package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.TicketTypeRequestDao;

@Service("ticketTypeDeleteService")
public class TicketTypeDeleteService implements ServiceInterface<TicketTypeRequestDao, Boolean> {

	@Autowired
	SqlSession sqlSession;

	@Override
	public Boolean doService(TicketTypeRequestDao reqDao) throws SQLException {
		int status;
		try {
			status = sqlSession.delete("TicketTypeMapper.ticketTypeDelete", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		if (status == 0)
			return false;
		else
			return true;

	}

}
