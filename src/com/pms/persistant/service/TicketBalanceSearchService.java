package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.persistant.dao.TicketRequestDao;
import com.pms.persistant.dao.TicketResponseDao;

@Service("ticketBalanceSearchService")
public class TicketBalanceSearchService implements ServiceInterface<TicketRequestDao, List<TicketResponseDao>> {
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<TicketResponseDao> doService(TicketRequestDao reqDao) throws SQLException {

		List<TicketResponseDao> ticketResponseDaoList = new ArrayList<>();

		TicketResponseDao resDao = sqlSession.selectOne("TicketMapper.getById", reqDao);
		if (resDao != null) {
			ticketResponseDaoList.add(resDao);
		}

		return ticketResponseDaoList;
	}

}
