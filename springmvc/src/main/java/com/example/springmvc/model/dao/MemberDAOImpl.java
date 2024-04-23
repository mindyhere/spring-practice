package com.example.springmvc.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.springmvc.model.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Inject
	SqlSession sqlSession;

	@Override
	public List<MemberDTO> memberList() {
		return sqlSession.selectList("member.memberList");
	}

	public void insertMember(MemberDTO dto) {
		sqlSession.insert("member.insertMember", dto);
	}

	public MemberDTO viewMember(String userid) {
		return sqlSession.selectOne("member.viewMember", userid);
	}

	public void deleteMember(String userid) {
		sqlSession.delete("member.deleteMember", userid);
	}

	public void updateMember(MemberDTO dto) {
		sqlSession.update("member.updateMember", dto);
	}

	public boolean checkPw(String userid, String passwd) {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		int count = sqlSession.selectOne("member.checkPw", map);
		if (count == 1)
			result = true;
		return result;
	}
}
