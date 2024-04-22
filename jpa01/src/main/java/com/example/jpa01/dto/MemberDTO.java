package com.example.jpa01.dto;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
	// DTO : 데이터전달객체. 데이터 교환 목적
	private String userid;
	private String passwd;
	private String name;
	private String email;
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date join_date;
}
