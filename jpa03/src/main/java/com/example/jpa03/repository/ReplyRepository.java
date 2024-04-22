package com.example.jpa03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa03.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	List<Reply> findByBoardIdx(int board_idx);

	int countByBoardIdx(int board_idx);
}