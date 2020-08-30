package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.TicketStatusRequestDao;
import com.pms.persistant.dao.UserStatusRequestDao;

@Service("ticketStautsRegisterService")
public class TicketStatusRegisterService implements ServiceInterface<TicketStatusRequestDao,Boolean>{

	@Autowired
	SqlSession sqlSession;

	public Boolean doService(TicketStatusRequestDao reqDao) throws SQLException {

		try {
			sqlSession.insert("TicketStatusMapper.ticketStatusInsert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}

}