package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.MemberTypeRequestDao;
import com.pms.persistant.dao.MemberTypeResponseDao;

@Service("memberTypeSelectOne")
public class MemberTypeSelectOne implements ServiceInterface<MemberTypeRequestDao,MemberTypeResponseDao>{
@Autowired
SqlSession session;
	@Override
	public MemberTypeResponseDao doService(MemberTypeRequestDao redDao) throws SQLException {
		MemberTypeResponseDao dao=session.selectOne("MembershipMapper.getData",redDao);
		return dao;
	}

	

}
