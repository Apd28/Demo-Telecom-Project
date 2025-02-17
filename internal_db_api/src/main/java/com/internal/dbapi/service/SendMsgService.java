package com.internal.dbapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.internal.dbapi.dto.SendMsgDTO;

@Service
public interface SendMsgService {

	
	List<SendMsgDTO> getAllMessages();
	
	void saveMessage(SendMsgDTO sendMsgDTO);
	
}
