package com.example.spring01;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnTest {

	private static final Logger logger = LoggerFactory.getLogger(DBConnTest.class);
	String driver = "com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost/web?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul";
	String userid="spring";
	String pwd="1234";
	
	@Test
	public void testConnection() throws Exception{
		Class.forName(driver);
		try {
			Connection conn=DriverManager.getConnection(url, userid, pwd);
			logger.info("mysql에 연결되었습니다.");
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
}
