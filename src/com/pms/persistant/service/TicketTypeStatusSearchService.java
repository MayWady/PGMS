package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.TicketTypeStatusRequestDao;
import com.pms.persistant.dao.TicketTypeStatusResponseDao;

@Service("tickettypeStatusSearchService")
public class TicketTypeStatusSearchService
		implements ServiceInterface<TicketTypeStatusRequestDao, List<TicketTypeStatusResponseDao>> {

	@Autowired
	SqlSession sqlSession;

	public List<TicketTypeStatusResponseDao> doService(TicketTypeStatusRequestDao reqDao) throws SQLException {
		List<TicketTypeStatusResponseDao> tickettypeStatusResponseDaoList = sqlSession
				.selectList("TicketTypeStatusMapper.getAllTicketTypeStatus", reqDao);

		return tickettypeStatusResponseDaoList;
	}

}
