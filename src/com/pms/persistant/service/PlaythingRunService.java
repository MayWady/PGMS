package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlaythingRequestDao;

@Service("playthingRunService")
public class PlaythingRunService implements ServiceInterface<PlaythingRequestDao,Boolean> {
@Autowired
SqlSession session;
	@Override
	public Boolean doService(PlaythingRequestDao redDao) throws SQLException {
		session.update("PlayMapper.updatePlaything",redDao);
		return true;
	}

}
