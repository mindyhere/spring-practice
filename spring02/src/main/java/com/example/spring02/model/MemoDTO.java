package com.example.spring02.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemoDTO {
	private int idx;
	private String writer;
	private String memo;
	private Date post_date;
	
	public MemoDTO(String writer, String memo) {
		super();
		this.writer = writer;
		this.memo = memo;
	}
}
