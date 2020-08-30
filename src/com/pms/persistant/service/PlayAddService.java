package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlayRequestDao;

@Service("playAddService")
public class PlayAddService implements ServiceInterface<PlayRequestDao, Boolean> {

	@Autowired
	SqlSession sqlSession;

	public Boolean doService(PlayRequestDao reqDao) throws SQLException {
		try {
			sqlSession.insert("PlayMapper.playInsert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
