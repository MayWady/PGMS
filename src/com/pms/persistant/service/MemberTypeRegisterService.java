package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.MemberTypeRequestDao;


@Service("memberTypeRegisterService")
public class MemberTypeRegisterService {
	@Autowired
	SqlSession sqlSession;

	public Boolean doService(MemberTypeRequestDao reqDao) throws SQLException {

		try {
			sqlSession.insert("MemberTypeMapper.membertypeInsert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}
}
