package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlaythingRequestDao;
import com.pms.persistant.dao.PlaythingResponseDao;

@Service("playthingSelectService")
public class PlaythingSelectService implements ServiceInterface<PlaythingRequestDao,List<PlaythingResponseDao>>{
@Autowired
SqlSession session;
	@Override
	public List<PlaythingResponseDao> doService(PlaythingRequestDao redDao) throws SQLException {
		List<PlaythingResponseDao> list=session.selectList("PlaythingSelectMapper.getAllPlaything");
		return list;
	}

}
