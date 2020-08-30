package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlayRequestDao;
import com.pms.persistant.dao.PlayResponseDao;

@Service("playSearchService")
public class PlaySearchService implements ServiceInterface<PlayRequestDao, List<PlayResponseDao>> {

	@Autowired
	SqlSession sqlSession;

	public List<PlayResponseDao> doService(PlayRequestDao reqDao) throws SQLException {
		List<PlayResponseDao> playResponseDaoList = sqlSession.selectList("PlayMapper.getAllPlay", reqDao);
		return playResponseDaoList;
	}

}
