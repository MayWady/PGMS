package com.pms.persistant.service;

import java.sql.SQLException;

public interface ServiceInterface<T,R> {
	public R doService(T redDao) throws SQLException;
}
