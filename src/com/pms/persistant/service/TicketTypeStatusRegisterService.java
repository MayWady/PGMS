package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.TicketTypeStatusRequestDao;



@Service("tickettypeStautsRegisterService")
public class TicketTypeStatusRegisterService implements ServiceInterface<TicketTypeStatusRequestDao,Boolean>{

	@Autowired
	SqlSession sqlSession;

	public Boolean doService(TicketTypeStatusRequestDao reqDao) throws SQLException {

		try {
			sqlSession.insert("TicketTypeStatusMapper.tickettypeStatusInsert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}

}