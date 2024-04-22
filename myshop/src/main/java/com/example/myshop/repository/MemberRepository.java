package com.example.myshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myshop.entity.Member;


public interface MemberRepository extends JpaRepository<Member, String>{
	Optional<Member> findByUserid(String userid);

	Optional<Member> findByUseridAndPasswd(String userid, String passwd);

}
