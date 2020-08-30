package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.UserStatusRequestDao;

@Service("userStatusDeleteService")
public class UserStatusDeleteService implements ServiceInterface<UserStatusRequestDao, Boolean>{

	@Autowired
	SqlSession sqlSession;
	@Override
	public Boolean doService(UserStatusRequestDao reqDao) throws SQLException {
		int status;
		try {
			status=sqlSession.delete("UserStatusMapper.userStatusDelete",reqDao);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		
		if(status==0) return false;
		else return true;
	}

}
