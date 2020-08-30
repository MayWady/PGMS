package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlayStatusRequestDao;
import com.pms.persistant.dao.PlayStatusResponseDao;


@Service("playStatusSearchService")
public class PlayStatusSearchService implements ServiceInterface<PlayStatusRequestDao,List<PlayStatusResponseDao>>{
	
	@Autowired
	SqlSession sqlSession;

	public List<PlayStatusResponseDao> doService(PlayStatusRequestDao reqDao) throws SQLException {
		List<PlayStatusResponseDao> playStatusResponseDaoList= sqlSession.selectList("TicketPlaythingStatusMapper.getAllPlayStatus", reqDao);
		
		return playStatusResponseDaoList;
	}


}
