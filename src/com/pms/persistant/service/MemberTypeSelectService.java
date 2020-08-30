package com.pms.persistant.service;
import com.pms.persistant.dao.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberTypeSelectService")
public class MemberTypeSelectService{
	@Autowired
	SqlSession session;
	public List<MemberTypeResponseDao> getList(){
		 List<MemberTypeResponseDao>list = session.selectList("MemberTypeMapper.getAllMemberType");
		 return list;
	}

}
