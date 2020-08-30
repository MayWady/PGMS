package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.TicketTypePlaythingRequestDao;

@Service("playthingTicketTypeIdSearchService")
public class PlaythingTicketTypeIdSearchService implements ServiceInterface<TicketTypePlaythingRequestDao, String> {

	@Autowired
	SqlSession sqlSession;

	@Override
	public String doService(TicketTypePlaythingRequestDao reqDao) throws SQLException {
		if (sqlSession.selectOne("PlayMapper.getPlaything", reqDao) == null)
			return "0";
		else
			return sqlSession.selectOne("PlayMapper.getPlaything", reqDao);
	}

}
