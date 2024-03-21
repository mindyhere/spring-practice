package com.example.spring03.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring03.model.message.MessageDAO;
import com.example.spring03.model.message.MessageDTO;
import com.example.spring03.model.message.PointDAO;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageDAO messageDao;

	@Autowired
	PointDAO pointDao;

	@Transactional // 트랜잭션 처리대상 method
	@Override
	public void insertMessage(MessageDTO dto) {
		messageDao.create(dto); // 메세지를 테이블(DB)에 저장
		pointDao.updatePoint(dto.getSender(), 10); // 포인트 부여(업데이트)
	}

}
