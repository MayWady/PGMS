package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlaythingStatusRequestDao;


@Service("playthingStautsRegisterService")
public class PlaythingStatusRegisterService implements ServiceInterface<PlaythingStatusRequestDao,Boolean>{

	@Autowired
	SqlSession sqlSession;

	public Boolean doService(PlaythingStatusRequestDao reqDao) throws SQLException {

		try {
			sqlSession.insert("PlaythingStatusMapper.playthingStautsInsert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}

}

