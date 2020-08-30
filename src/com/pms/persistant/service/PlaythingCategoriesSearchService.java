package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlaythingCategoriesRequestDao;
import com.pms.persistant.dao.PlaythingCategoriesResponseDao;

@Service("playthingCategoriesSearchService")
public class PlaythingCategoriesSearchService
		implements ServiceInterface<PlaythingCategoriesRequestDao, List<PlaythingCategoriesResponseDao>> {

	@Autowired
	SqlSession sqlSession;

	public List<PlaythingCategoriesResponseDao> doService(PlaythingCategoriesRequestDao reqDao) throws SQLException {
		List<PlaythingCategoriesResponseDao> playthingCategoriesRequestDaoList = sqlSession
				.selectList("PlaythingCategoriesMapper.getAllPlaythingCategories", reqDao);
		return playthingCategoriesRequestDaoList;
	}

}
