package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlaythingRequestDao;

@Service("playthingUpdateService")
public class PlaythingUpdateService implements ServiceInterface<PlaythingRequestDao, Boolean> {

	@Autowired
	SqlSession sqlSession;

	public Boolean doService(PlaythingRequestDao reqDao) throws SQLException {
		try {
			sqlSession.update("PlaythingMapper.playthingUpdate", reqDao);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
		return true;
	}

}
