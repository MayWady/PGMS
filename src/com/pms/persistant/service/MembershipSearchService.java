package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.MembershipRequestDao;
import com.pms.persistant.dao.MembershipResponseDao;

@Service("membershipSearchService")
public class MembershipSearchService implements ServiceInterface<MembershipRequestDao, List<MembershipResponseDao>> {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<MembershipResponseDao> doService(MembershipRequestDao reqDao) throws SQLException {
		List<MembershipResponseDao> membershipResponseDaoList = sqlSession
				.selectList("MembershipMapper.getAllMembership", reqDao);
		return membershipResponseDaoList;
	}
}
