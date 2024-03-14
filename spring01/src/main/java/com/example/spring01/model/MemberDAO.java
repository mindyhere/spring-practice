package com.example.spring01.model;

import java.util.List;

// 컨트롤러-인터페이스-구현체클래스의 디자인패턴으로 실습
public interface MemberDAO {
	List<MemberDTO> list();

	void insert(MemberDTO dto);

	MemberDTO detail(String userid);

	void delete(String userid);

	void update(MemberDTO dto);

	boolean check_passwd(String userid, String passwd);
}
