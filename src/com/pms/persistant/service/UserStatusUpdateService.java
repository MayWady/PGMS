package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.UserStatusRequestDao;

@Service("userStatusUpdateService")
public class UserStatusUpdateService implements ServiceInterface<UserStatusRequestDao,Boolean>{
	
	@Autowired
	SqlSession sqlSession;
	@Override
	public Boolean doService(UserStatusRequestDao reqDao) throws SQLException {
		try {
			sqlSession.update("UserStatusMapper.userStatusUpdate", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
