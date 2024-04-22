package com.example.jpa03.dto;

import java.util.Date;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReplyDTO {
	private int idx;
	private int boardIdx;
	private String replyText;
	private Date regdate;
}