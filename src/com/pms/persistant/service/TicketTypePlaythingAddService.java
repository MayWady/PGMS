package com.pms.persistant.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.pms.persistant.dao.TicketTypePlaythingRequestDao;

@Service("ticketTypePlaythingAddService")
public class TicketTypePlaythingAddService implements ServiceInterface<TicketTypePlaythingRequestDao, Boolean>{
@Autowired
SqlSession session;
	@Override
	public Boolean doService(TicketTypePlaythingRequestDao redDao) throws SQLException {
		session.insert("TicketTypeMapper.ticketTypePlaything",redDao);
		return true;
	}

}
