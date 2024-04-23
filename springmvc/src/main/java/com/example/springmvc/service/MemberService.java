package com.example.springmvc.service;

import java.util.List;

import com.example.springmvc.model.dto.MemberDTO;

public interface MemberService {
	public List<MemberDTO> memberList();

	public void insertMember(MemberDTO dto);
	
	public MemberDTO viewMember(String userid);

	public void deleteMember(String userid);

	public void updateMember(MemberDTO dto);
	
	public boolean checkPw(String userid, String passwd);
}
