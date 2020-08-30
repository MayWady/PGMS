package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.MemberTypeRequestDao;


@Service("memberTypeDeleteService")
public class MemberTypeDeleteService implements ServiceInterface<MemberTypeRequestDao, Boolean> {
	int status;
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public Boolean doService(MemberTypeRequestDao redDao) throws SQLException {
		try {
			
			status=sqlSession.delete("MemberTypeMapper.memberTypeDelete", redDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		if(status == 0) {
			return false;
		}else {
		return true;
	}
	}

}

