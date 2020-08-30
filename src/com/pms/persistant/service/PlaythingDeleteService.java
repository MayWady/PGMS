package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlaythingRequestDao;

@Service("playthingDeleteService")
public class PlaythingDeleteService implements ServiceInterface<PlaythingRequestDao, Boolean> {

	@Autowired
	SqlSession sqlSession;

	public Boolean doService(PlaythingRequestDao reqDao) throws SQLException {
		try {
			sqlSession.delete("PlaythingMapper.playthingDelete", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
