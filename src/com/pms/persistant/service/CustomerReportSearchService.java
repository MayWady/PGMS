package com.pms.persistant.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.CustomerReportRequestDao;
import com.pms.persistant.dao.CustomerReportResponseDao;

@Service("customerReportService")
public class CustomerReportSearchService implements ServiceInterface<CustomerReportRequestDao, List<CustomerReportResponseDao>> {

	@Autowired
	SqlSession sqlSession;
	
	public List<CustomerReportResponseDao> doService(CustomerReportRequestDao reqDao) throws SQLException {
		List<CustomerReportResponseDao>  customerResponseDaoList = sqlSession.selectList("CustomerReportMapper.getCustomerReport", reqDao);

		return customerResponseDaoList;
	}

}
