package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.persistant.dao.TicketTypeStatusRequestDao;

@Service("tickettypeStatusDeleteService")
public class TicketTypeStatusDeleteService implements ServiceInterface<TicketTypeStatusRequestDao, Boolean> {

	@Autowired
	SqlSession sqlSession;

	@Override
	public Boolean doService(TicketTypeStatusRequestDao reqDao) throws SQLException {
		int status;
		try {
			status=sqlSession.delete("TicketTypeStatusMapper.tickettypeStatusDelete", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		if(status==0) return false;
		else return true;
	}

}
