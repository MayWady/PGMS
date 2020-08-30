package com.pms.persistant.service;

import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.persistant.dao.TopupAmountRequestDao;

@Service("topupAmountRegisterService")
public class TopupAmountRegisterService implements ServiceInterface<TopupAmountRequestDao, Boolean> {
	@Autowired
	SqlSession sqlSession;

	@Override
	public Boolean doService(TopupAmountRequestDao reqDao) throws SQLException {

		try {
			sqlSession.insert("TopupAmountMapper.topupAmountInsert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}
	
	public Boolean doTopupService(TopupAmountRequestDao reqDao) throws SQLException {

		try {
			sqlSession.insert("TopupAmountMapper.insert", reqDao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}

}
