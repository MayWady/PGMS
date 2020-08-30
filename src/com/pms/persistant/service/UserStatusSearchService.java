package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.UserStatusRequestDao;
import com.pms.persistant.dao.UserStatusResponseDao;

@Service("userStatusSearchService")
public class UserStatusSearchService implements ServiceInterface<UserStatusRequestDao, List<UserStatusResponseDao>> {

	@Autowired
	SqlSession sqlSession;

	public List<UserStatusResponseDao> doService(UserStatusRequestDao reqDao) throws SQLException {
		List<UserStatusResponseDao> userStatusResponseDaoList = sqlSession
				.selectList("UserStatusMapper.getAllUserStatus", reqDao);

		return userStatusResponseDaoList;
	}

}
