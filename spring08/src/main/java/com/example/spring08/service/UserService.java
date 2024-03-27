package com.example.spring08.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring08.model.dto.UserDTO;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	SqlSession sqlSession;

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		Map<String, Object> user = sqlSession.selectOne("user.detail", userid);
		if (user == null)
			throw new UsernameNotFoundException(userid);
		// throws: 함수 단위 예외처리
		// throw: 강제 예외 발생
		List<GrantedAuthority> authority = new ArrayList<>();
		// 사용권한
		authority.add(new SimpleGrantedAuthority(user.get("AUTHORITY").toString()));

		return new UserDTO(user.get("USERNAME").toString(), user.get("PASSWORD").toString(),
				(Integer) Integer.valueOf(user.get("ENABLED").toString()) == 1, true, true, true, authority,
				user.get("USERNAME").toString());
	}

}
