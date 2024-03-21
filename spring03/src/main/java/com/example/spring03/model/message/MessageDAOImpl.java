package com.example.spring03.model.message;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAOImpl implements MessageDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void create(MessageDTO dto) {
		sqlSession.insert("message.create", dto);
	}
}
