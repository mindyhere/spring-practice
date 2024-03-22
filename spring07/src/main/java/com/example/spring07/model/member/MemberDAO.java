package com.example.spring07.model.member;

public interface MemberDAO {
	MemberDTO login(String userid, String passwd);

	void join(MemberDTO dto);
}
