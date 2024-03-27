package com.example.spring01.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class MemberDAOImplTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImplTest.class);

	@Autowired
	MemberDAO memberDao;

	@Test
	void testList() {
		logger.info("회원목록: " + memberDao.list());
	}

	@Test
	void testInsert() {
		MemberDTO dto = new MemberDTO();
		dto.setUserid("kim2");
		dto.setPasswd("1234");
		dto.setName("김철수");
		dto.setEmail("kim@naver.com");
		memberDao.insert(dto);
		logger.info("저장완료...");
	}

	@Test
	void testDetail() {
		logger.info("dto: " + memberDao.detail("park"));
	}

	@Test
	void testDelete() {
		memberDao.delete("kim2");
		logger.info("삭제완료...");
	}

	@Test
	void testUpdate() {
		MemberDTO dto = new MemberDTO();
		dto.setUserid("kim2");
		dto.setPasswd("2222");
		dto.setName("김철호");
		dto.setEmail("kim@daum.net");
		memberDao.update(dto);
		logger.info("업데이트 완료...");
	}

	@Test
	void testCheck_passwd() {
		logger.info("비밀번호체크: " + memberDao.check_passwd("kim2", "1234"));
		logger.info("비밀번호체크: " + memberDao.check_passwd("kim2", "2222"));
	}

}
