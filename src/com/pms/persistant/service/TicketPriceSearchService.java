package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.persistant.dao.TicketTypeRequestDao;
import com.pms.persistant.dao.TicketTypeResponseDao;

@Service("ticketPriceSearchService")
public class TicketPriceSearchService implements ServiceInterface<TicketTypeRequestDao, List<TicketTypeResponseDao>> {
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<TicketTypeResponseDao> doService(TicketTypeRequestDao reqDao) throws SQLException {

		List<TicketTypeResponseDao> ticketTypeResponseDaoList = new ArrayList<>();

		TicketTypeResponseDao resDao = sqlSession.selectOne("TicketPriceMapper.getById", reqDao);
		if (resDao != null) {
			ticketTypeResponseDaoList.add(resDao);
		}

		return ticketTypeResponseDaoList;
	}

}
