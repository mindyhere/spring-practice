package com.example.spring03.model.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PointUserDTO {
	private String userid;
	private String pwd;
	private String name;
	private int point;
}
