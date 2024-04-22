package com.example.jpa03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa03.entity.Attach;

public interface AttachRepository extends JpaRepository<Attach, String> {
	void deleteByFileName(String fileName);

	int countByBoardIdx(int board_idx);
}