package com.example.myshop.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myshop.dto.MemberDTO;
import com.example.myshop.entity.Member;
import com.example.myshop.repository.MemberRepository;

@RestController // 리턴 데이터(기본)
@RequestMapping("/api/member/*")
public class MemberApi {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	ModelMapper modelMapper;

	@PostMapping("join")
	public Map<String, Object> join(@ModelAttribute MemberDTO dto) {
		// @ModelAttribute → 생략가능
		Member m = modelMapper.map(dto, Member.class);
		// dto → entity, entity → dto
		m.setLevel(1); // 일반회원
		Member m2 = memberRepository.save(m);
		Map<String, Object> map = new HashMap<>();
		if (m2 != null) {
			map.put("message", "success");
		} else {
			map.put("message", "error");
		}
		return map;
	}

	@PostMapping("login")
	public Map<String, Object> login(MemberDTO dto) {
		Optional<Member> result = memberRepository.findByUseridAndPasswd(dto.getUserid(), dto.getPasswd());
		Map<String, Object> map = new HashMap<>();
		if (result.isPresent()) {
			Member m = result.get();
			map.put("message", "success");
			map.put("name", m.getName());
			map.put("level", m.getLevel());
		} else {
			map.put("message", "error");
		}
		System.out.println("map: " + map);
		return map;
	}

}
