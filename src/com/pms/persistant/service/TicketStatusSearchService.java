package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.TicketStatusRequestDao;
import com.pms.persistant.dao.TicketStatusResponseDao;
import com.pms.persistant.dao.UserStatusRequestDao;
import com.pms.persistant.dao.UserStatusResponseDao;

@Service("ticketStatusSearchService")
public class TicketStatusSearchService implements ServiceInterface<TicketStatusRequestDao,List<TicketStatusResponseDao>>{
	
	@Autowired
	SqlSession sqlSession;

	public List<TicketStatusResponseDao> doService(TicketStatusRequestDao reqDao) throws SQLException {
		List<TicketStatusResponseDao> ticketStatusResponseDaoList= sqlSession.selectList("TicketStatusMapper.getAllTicketStatus", reqDao);
		
		return ticketStatusResponseDaoList;
	}


}
