package com.example.spring03.model.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<ReplyDTO> list(int board_idx, int start, int end) {
		// 댓글 리스트
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("board_idx", board_idx);
		List<ReplyDTO> items = sqlSession.selectList("reply.list", map);
		return items;
	}

	@Override
	public int count(int board_idx) {
		// 댓글 개수
		return sqlSession.selectOne("reply.count", board_idx);
	}

	@Override
	public void insert(ReplyDTO dto) {
		sqlSession.insert("reply.insert", dto);
	}

	@Override
	public void update(ReplyDTO dto) {
		// 댓글 수정
		sqlSession.update("reply.update", dto);
	}

	@Override
	public void delete(int idx) {
		// 댓글 삭제
		sqlSession.delete("reply.delete", idx);

	}

	@Override
	public ReplyDTO detail(int idx) {
		// 댓글 상세
		return sqlSession.selectOne("reply.detail", idx);
	}
}
