package com.example.spring01;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// JUnit 4.0으로 현재 클래스를 실행시킴
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MybatisTest {
	private static final Logger logger = LoggerFactory.getLogger(DBConnTest.class);

	@Autowired
	SqlSession sqlSession;

	@Test // JUnit 테스트 함수
	public void testSession() {
		logger.info("sqlSession : ", sqlSession);
		logger.info("mybatis 연결성공...");
	}
}
