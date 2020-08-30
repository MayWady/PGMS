package com.pms.persistant.service;

import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ticketMemberDiscountSearchService")
public class TicketMemberDiscountSearchService implements ServiceInterface<String, String> {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public String doService(String redDao) throws SQLException {
		if(sqlSession.selectOne("MemberDiscountMapper.getById", redDao)==null)
			return "0";
		else
		return sqlSession.selectOne("MemberDiscountMapper.getById", redDao);
	}

}
