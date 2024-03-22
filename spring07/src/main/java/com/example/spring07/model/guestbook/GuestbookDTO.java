package com.example.spring07.model.guestbook;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestbookDTO {
	private String _id;
	private String name;
	private String email;
	private String contents;
	private Date post_date;
}
