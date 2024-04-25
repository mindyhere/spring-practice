package com.example.springmvc.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.springmvc.model.dao.MemberDAOImpl;
import com.example.springmvc.model.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDAOImpl memberDao;
	
	@Override
	public List<MemberDTO> memberList() {
		List<MemberDTO> list=memberDao.memberList();
		String str="";
		for(MemberDTO dto: list) {
			str=dto.getUserid().replace("<", "&lt;");
			str=dto.getUserid().replace(">", "&gt;");
			dto.setUserid(str);
			str=dto.getPasswd().replace("<", "&lt;");
			str=dto.getPasswd().replace(">", "&gt;");
			dto.setPasswd(str);
			str=dto.getName().replace("<", "&lt;");
			str=dto.getName().replace(">", "&gt;");
			dto.setName(str);
			str=dto.getEmail().replace("<", "&lt;");
			str=dto.getEmail().replace(">", "&gt;");
			dto.setEmail(str);
		}
		return list;
	}

}
