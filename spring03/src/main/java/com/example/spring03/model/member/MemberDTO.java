package com.example.spring03.model.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
	private String userid;
	private String passwd;
	private String name;
}
