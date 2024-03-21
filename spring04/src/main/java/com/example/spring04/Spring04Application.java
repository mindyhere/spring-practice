package com.example.spring04;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Spring04Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring04Application.class, args);
	}

	@Bean
	SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	@Bean
	SqlSessionTemplate sqlSession(SqlSessionFactory factory){
	return new SqlSessionTemplate(factory);
	}
	
}
