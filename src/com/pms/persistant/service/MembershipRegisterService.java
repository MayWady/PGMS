package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.MembershipRequestDao;


@Service("membershipRegisterService")
public class MembershipRegisterService implements ServiceInterface<MembershipRequestDao, Boolean> {

	@Autowired
	SqlSession sqlSession;

	@Override
	public Boolean doService(MembershipRequestDao reqDao) throws SQLException {
		try {
			sqlSession.insert("MembershipMapper.membershipInsert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}
}
