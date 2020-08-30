package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.UserRequestDao;
import com.pms.persistant.dao.UserResponseDao;

@Service("userSearchService")
public class UserSearchService implements ServiceInterface<UserRequestDao,List<UserResponseDao>>{

	@Autowired
	SqlSession sqlSession;

	public List<UserResponseDao> doService(UserRequestDao reqDao) throws SQLException {
		List<UserResponseDao> userResponseDaoList= sqlSession.selectList("UserMapper.getAllUser", reqDao);
		
		return userResponseDaoList;
	}

}
