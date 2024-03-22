package com.example.spring07.model.memo;

import java.util.List;

public interface MemoDAO {
	List<MemoDTO> list();

	void insert(MemoDTO dto);

	MemoDTO detail(String _id);

	void update(MemoDTO dto);

	void delete(String _id);

}
