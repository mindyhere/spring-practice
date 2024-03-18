package com.example.spring03.service.email;

import com.example.spring03.model.email.EmailDTO;

public interface EmailService {
	void sendMail(EmailDTO dto);
}
