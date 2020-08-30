package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlaythingRequestDao;
import com.pms.persistant.dao.PlaythingResponseDao;

@Service("playthingSearchService")
public class PlaythingSearchService implements ServiceInterface<PlaythingRequestDao, List<PlaythingResponseDao>> {

	@Autowired
	SqlSession sqlSession;

	public List<PlaythingResponseDao> doService(PlaythingRequestDao reqDao) throws SQLException {
		List<PlaythingResponseDao> playthingResponseDaoList = sqlSession.selectList("PlaythingMapper.getAllPlaything",
				reqDao);
		return playthingResponseDaoList;
	}

}
