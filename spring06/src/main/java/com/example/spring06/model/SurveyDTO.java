package com.example.spring06.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SurveyDTO {
	private int survey_idx; // 문제번호
	private String question; // 문제내용
	private String ans1; // 답1
	private String ans2; // 답2
	private String ans3; // 답3
	private String ans4; // 답4
	private String status; // 진행상태
}
