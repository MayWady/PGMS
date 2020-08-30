package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.MemberTypeRequestDao;
import com.pms.persistant.dao.MemberTypeResponseDao;


@Service("memberTypeSearchService")
public class MemberTypeSearchService implements ServiceInterface<MemberTypeRequestDao, List<MemberTypeResponseDao>>{
	@Autowired
	SqlSession sqlSession;
	@Override
	public List<MemberTypeResponseDao> doService(MemberTypeRequestDao reqDao) throws SQLException {
		List<MemberTypeResponseDao> memberTypeResponseDaoList=new ArrayList();
		if(reqDao.getMemberTypename().equals("")) {
			System.out.println("a");
		 memberTypeResponseDaoList = sqlSession.selectList("MemberTypeMapper.getAllMemberType");
		}
		else
			memberTypeResponseDaoList = sqlSession.selectList("MemberTypeMapper.getMemberType",reqDao);
		return memberTypeResponseDaoList;
	}

}
