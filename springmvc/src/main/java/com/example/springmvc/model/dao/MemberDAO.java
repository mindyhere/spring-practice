package com.example.springmvc.model.dao;

import java.util.List;

import com.example.springmvc.model.dto.MemberDTO;

public interface MemberDAO {
	public List<MemberDTO> memberList();
}
