package com.example.spring03.model.message;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageDTO {
	private int idx;
	private String receive_id;
	private String sender;
	private String message;
	private Date open_date;
	private Date send_date;

}
