package com.example.spring04.model;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GuestbookDAO {
	@Select("select * from guestbook order by idx desc")
	List<GuestbookDTO> list();

	@Insert("insert into guestbook (idx, name, email, content) values (guestbook_seq.nextval, #{name}, #{email}, #{content})")
	void insert(GuestbookDTO dto);

	@Select("select * from guestbook where idx=#{idx}")
	GuestbookDTO view(int idx);

	@Update("update guestbook set name=#{name}, email=#{email}, content=#{content} where idx=#{idx}")
	void update(GuestbookDTO dto);

	@Delete("delete from guestbook where idx=#{idx}")
	void delete(int idx);
}
