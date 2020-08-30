package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.UserRequestDao;
import com.pms.persistant.dao.UserResponseDao;

@Service("loginService")
public class LoginService implements ServiceInterface<UserRequestDao, List<UserResponseDao>> {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<UserResponseDao> doService(UserRequestDao reqDao) throws SQLException {
		List<UserResponseDao> userResponseDaoList = new ArrayList<>();

		UserResponseDao resDao = sqlSession.selectOne("LoginMapper.getById", reqDao);
		if (resDao != null) {
			userResponseDaoList.add(resDao);
		}

		return userResponseDaoList;
	}

}
