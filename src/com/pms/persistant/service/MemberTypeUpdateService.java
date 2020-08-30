package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.MemberTypeRequestDao;


@Service("memberTypeUpdateService")
public class MemberTypeUpdateService implements ServiceInterface<MemberTypeRequestDao, Boolean> {

	@Autowired
	SqlSession sqlSession;

	
	@Override
	public Boolean doService(MemberTypeRequestDao redDao) throws SQLException {
		try {
			sqlSession.update("MemberTypeMapper.memberTypeUpdate", redDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}