package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlayRequestDao;

@Service("playUpdateService")
public class PlayUpdateService implements ServiceInterface<PlayRequestDao, Boolean>{
@Autowired 
SqlSession session;
	@Override
	public Boolean doService(PlayRequestDao redDao) throws SQLException {
		session.update("PlayMapper.updatePlay",redDao);
		return true;
	}

}
