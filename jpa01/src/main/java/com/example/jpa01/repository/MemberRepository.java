package com.example.jpa01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa01.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	// 인터페이스 extands 인터페이스 (↔ 인터페이스 implements 클래스)
	// JpaRepository<T, ID> → JpaRepository<자료형, pk>
	Optional<Member> findByUseridAndPasswd(String userid, String passwd);
	// null 처리		 findBy필드명And필드명... → camelCase로 정의
}
