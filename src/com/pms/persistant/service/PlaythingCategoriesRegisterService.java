package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlaythingCategoriesRequestDao;

@Service("playthingCategoriesRegisterService")
public class PlaythingCategoriesRegisterService implements ServiceInterface<PlaythingCategoriesRequestDao, Boolean> {

	@Autowired
	SqlSession sqlSession;

	public Boolean doService(PlaythingCategoriesRequestDao reqDao) throws SQLException {
		try {
			sqlSession.insert("PlaythingCategoriesMapper.playthingCategoriesInsert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
