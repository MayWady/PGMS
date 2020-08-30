package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.persistant.dao.UserStatusRequestDao;

@Service("userStautsRegisterService")
public class UserStatusRegisterService implements ServiceInterface<UserStatusRequestDao,Boolean>{

	@Autowired
	SqlSession sqlSession;

	public Boolean doService(UserStatusRequestDao reqDao) throws SQLException {

		try {
			sqlSession.insert("UserStatusMapper.userStautsInsert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}

}
