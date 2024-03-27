package com.example.spring01.controller;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
@SpringBootTest  // 스프링부트 환경 테스트
class MainControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(MainControllerTest.class);

	@Autowired
	WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	SqlSession sqlSession;

	// 테스트 전에 실행할 코드
	@BeforeEach
	void setUp() throws Exception {
		// 가상으로 mvc 설정을 테스트 하는 코드
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		logger.info("1 setup...");
	}

	@Test
	void testMain() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"));
		logger.info("2 test main...");
	}

	@Test
	void testMulti_table() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/multi_table.do"));
		logger.info("3 test Multi_table...");
	}

	@Test
	void testTable_result() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/table_result.do"));
		logger.info("4 test table_result...");
	}

}
