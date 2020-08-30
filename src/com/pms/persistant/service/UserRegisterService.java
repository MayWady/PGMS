package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.UserRequestDao;

@Service("userRegisterService")
public class UserRegisterService implements ServiceInterface<UserRequestDao,Boolean> {

	@Autowired
	SqlSession sqlSession;
	
	public Boolean doService(UserRequestDao reqDao) throws SQLException {
	
		try {
			sqlSession.insert("UserMapper.userInsert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
