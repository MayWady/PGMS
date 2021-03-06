package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.MembershipStatusRequestDao;


@Service("membershipStautsRegisterService")
public class MembershipStatusRegisterService implements ServiceInterface<MembershipStatusRequestDao,Boolean>{

	@Autowired
	SqlSession sqlSession;

	public Boolean doService(MembershipStatusRequestDao reqDao) throws SQLException {

		try {
			sqlSession.insert("MembershipStatusMapper.membershipStautsInsert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}

}
