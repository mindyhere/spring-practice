package com.example.spring06.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnswerDTO {
	private int answer_idx; // 응답 일련번호
	private int survey_idx; // 문제 번호
	private int num; // 선택번호
	private int sum_num; // 응답횟수
	private double rate; // 응답률
}
