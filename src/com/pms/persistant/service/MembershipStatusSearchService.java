package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.MembershipStatusRequestDao;
import com.pms.persistant.dao.MembershipStatusResponseDao;


@Service("membershipStatusSearchService")
public class MembershipStatusSearchService implements ServiceInterface<MembershipStatusRequestDao,List<MembershipStatusResponseDao>>{

	@Autowired
	SqlSession sqlSession;

	public List<MembershipStatusResponseDao> doService(MembershipStatusRequestDao reqDao) throws SQLException {
		List<MembershipStatusResponseDao> membershipStatusResponseDaoList= sqlSession.selectList("MembershipStatusMapper.getAllMembershipStatus", reqDao);
		
		return membershipStatusResponseDaoList;
	}

}
