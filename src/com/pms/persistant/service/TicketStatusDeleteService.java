package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.TicketStatusRequestDao;

@Service("ticketStatusDeleteService")
public class TicketStatusDeleteService implements ServiceInterface<TicketStatusRequestDao, Boolean>{

	@Autowired
	SqlSession sqlSession;
	@Override
	public Boolean doService(TicketStatusRequestDao reqDao) throws SQLException {
		int status;
		try {
			status=sqlSession.delete("TicketStatusMapper.ticketStatusDelete",reqDao);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		if(status==0) return false;
		
		else return true;
	}

}
