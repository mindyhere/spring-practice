package com.example.spring01.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository	
public class MemberDAOImpl implements MemberDAO {
	@Autowired	// 의존관계주입. 스프링 객체생성&연결
	SqlSession sqlSession;

	@Override
	public List<MemberDTO> list() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("member.list");
	}

	@Override
	public void insert(MemberDTO dto) {
		// TODO Auto-generated method stub
		sqlSession.insert("member.insert", dto);

	}

	@Override
	public MemberDTO detail(String userid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.detail", userid);
	}

	@Override
	public void delete(String userid) {
		// TODO Auto-generated method stub
		sqlSession.delete("member.delete", userid);

	}

	@Override
	public void update(MemberDTO dto) {
		// TODO Auto-generated method stub
		sqlSession.update("member.update", dto);

	}

	@Override
	public boolean check_passwd(String userid, String passwd) {
		// TODO Auto-generated method stub
		boolean result = false;
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		int count = sqlSession.selectOne("member.check_passwd", map);
		if (count == 1)
			result = true;
		return result;
	}

}
