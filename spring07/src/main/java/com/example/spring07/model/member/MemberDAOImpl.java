package com.example.spring07.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	MongoTemplate mongoTemplate;  // mongodb 실행

	@Override
	public MemberDTO login(String userid, String passwd) {
		Query query = new Query(new Criteria("_id").is(userid).and("passwd").is(passwd));
		//							↔ where _id=?			   and passwd=?
		List<MemberDTO> items = mongoTemplate.find(query, MemberDTO.class, "member");
		//										   조건 	  mapper클래스		테이블

		if (items.size() > 0) {  // 레코드가 있으면
			return items.get(0);  // 첫번째 레코드
		} else {
			return null;
		}
	}

	@Override
	public void join(MemberDTO dto) {
		mongoTemplate.insert(dto, "member");
	}

}
