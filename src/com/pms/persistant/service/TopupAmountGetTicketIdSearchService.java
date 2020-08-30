package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.persistant.dao.TopupAmountRequestDao;
import com.pms.persistant.dao.TopupAmountResponseDao;

@Service("topupAmountGetTicketIdSearchService")
public class TopupAmountGetTicketIdSearchService implements ServiceInterface<TopupAmountRequestDao, List<TopupAmountResponseDao>> {
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<TopupAmountResponseDao> doService(TopupAmountRequestDao reqDao) throws SQLException {

		List<TopupAmountResponseDao> topupAmountResponseDaoList = new ArrayList<>();

		TopupAmountResponseDao resDao = sqlSession.selectOne("TopupAmountMapper.getTicketId", reqDao);
		if (resDao != null) {
			topupAmountResponseDaoList.add(resDao);
		}

		return topupAmountResponseDaoList;
	}
}
