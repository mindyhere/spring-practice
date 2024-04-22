package com.example.jpa02.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa02.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	// JpaRepository<테이블클래스, PK자료형> → 기본기능 포함
	Optional<Member> findByUseridAndPasswd(String userid, String passwd);
	// Optional → null 처리
	// findByUseridAndPasswd → where 필드명=? and 필드명=?
}
