package com.pms.persistant.service;

import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.pms.persistant.dao.TopupAmountRequestDao;
import com.pms.persistant.dao.TopupAmountResponseDao;

@Service("topupAmountSearchService")
public class TopupAmountSearchService implements ServiceInterface<TopupAmountRequestDao, List<TopupAmountResponseDao>> {
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<TopupAmountResponseDao> doService(TopupAmountRequestDao reqDao) throws SQLException {
		List<TopupAmountResponseDao> topupAmountResponseDaoList = sqlSession
				.selectList("TopupAmountMapper.getAllTopupAmount", reqDao);
		return topupAmountResponseDaoList;
	}
}
