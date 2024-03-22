package com.example.spring07.model.memo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class MemoDAOImpl implements MemoDAO {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<MemoDTO> list() {
		Query query = new Query().with(Sort.by(Sort.Direction.DESC, "post_date"));
		//								정렬					내림차순		날짜
		return (List<MemoDTO>) mongoTemplate.find(query, MemoDTO.class, "memo");
		//										  검색조건		 클래스		테이블
	}

	@Override
	public void insert(MemoDTO dto) {
		dto.setPost_date(new Date());	// 현재날짜 지정
		mongoTemplate.insert(dto, "memo");
	}

	@Override
	public MemoDTO detail(String _id) {
		// _id에 해당하는 도큐먼트 1개를 찾아서 리턴
		return mongoTemplate.findById(_id, MemoDTO.class, "memo");
		//							  key		클래스	   테이블
	}

	@Override
	public void update(MemoDTO dto) {
		Query query = new Query(new Criteria("_id").is(dto.get_id()));
		// 수정할 내용						  primary key
		
		Update update = new Update();
		//			필드명		변경할 값
		update.set("writer", dto.getWriter());
		update.set("memo", dto.getMemo());
		
		// upsert : update(_id가 있으면) or insert(_id가 없으면)
		mongoTemplate.upsert(query, update, MemoDTO.class, "memo");
	}

	@Override
	public void delete(String _id) {
		// _id에 해당하는 도큐먼트를 찾아서
		Query query = new Query(new Criteria("_id").is(_id));
		// 제거
		mongoTemplate.remove(query, "memo");
	}
}
