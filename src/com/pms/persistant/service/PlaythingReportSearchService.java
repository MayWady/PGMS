package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.PlaythingReportRequestDao;
import com.pms.persistant.dao.PlaythingReportResponseDao;

@Service("playthingReportService")
public class PlaythingReportSearchService implements ServiceInterface<PlaythingReportRequestDao, List<PlaythingReportResponseDao>> {

	@Autowired
	SqlSession sqlSession;
	
	public List<PlaythingReportResponseDao> doService(PlaythingReportRequestDao reqDao) throws SQLException {
		List<PlaythingReportResponseDao>  customerResponseDaoList = sqlSession.selectList("ZinKoLattMapper.getPlaythingReport", reqDao);

		return customerResponseDaoList;
	}

}
