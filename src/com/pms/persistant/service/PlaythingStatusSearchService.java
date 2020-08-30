package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlaythingStatusRequestDao;
import com.pms.persistant.dao.PlaythingStatusResponseDao;


@Service("playthingStatusSearchService")
public class PlaythingStatusSearchService implements ServiceInterface<PlaythingStatusRequestDao,List<PlaythingStatusResponseDao>>{

	@Autowired
	SqlSession sqlSession;

	public List<PlaythingStatusResponseDao> doService(PlaythingStatusRequestDao reqDao) throws SQLException {
		List<PlaythingStatusResponseDao> playthingStatusResponseDaoList= sqlSession.selectList("PlaythingStatusMapper.getAllPlaythingStatus", reqDao);
		
		return playthingStatusResponseDaoList;
	}

}

