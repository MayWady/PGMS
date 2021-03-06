package com.pms.persistant.service;

import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.persistant.dao.TopupAmountRequestDao;

@Service("topupAmountUpdateService")
public class TopupAmountUpdateService implements ServiceInterface<TopupAmountRequestDao, Boolean> {
	@Autowired
	SqlSession sqlSession;

	@Override
	public Boolean doService(TopupAmountRequestDao reqDao) throws SQLException {
		try {
			sqlSession.update("TopupAmountMapper.topupAmountUpdate", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
