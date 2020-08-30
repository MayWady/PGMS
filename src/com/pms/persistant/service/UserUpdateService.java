package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.UserRequestDao;

@Service("userUpdateService")
public class UserUpdateService implements ServiceInterface<UserRequestDao,Boolean>{
	
	@Autowired
	SqlSession sqlSession;
	@Override
	public Boolean doService(UserRequestDao reqDao) throws SQLException {
		try {
			sqlSession.update("UserMapper.userUpdate", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
