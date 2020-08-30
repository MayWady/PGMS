package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlayStatusRequestDao;

@Service("playStatusDeleteService")
public class PlayStatusDeleteService implements ServiceInterface<PlayStatusRequestDao, Boolean>{

	@Autowired
	SqlSession sqlSession;
	@Override
	public Boolean doService(PlayStatusRequestDao reqDao) throws SQLException {
		try {
			sqlSession.delete("TicketPlaythingStatusMapper.playStatusDelete",reqDao);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
