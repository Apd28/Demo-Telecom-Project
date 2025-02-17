package com.internal.dbapi.serviceimpl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internal.dbapi.dto.SendMsgDTO;
import com.internal.dbapi.model.MessageStatus;
import com.internal.dbapi.model.SendMsg;
import com.internal.dbapi.model.User;
import com.internal.dbapi.repository.SendMsgRepository;
import com.internal.dbapi.repository.UserRepository;
import com.internal.dbapi.service.SendMsgService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SendMsgServiceImpl implements SendMsgService {
	@Autowired
    private SendMsgRepository sendMsgRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SendMsgDTO> getAllMessages() {
        return sendMsgRepository.findAll().stream()
                .map(msg -> new SendMsgDTO(
                        msg.getId(),
                        msg.getMobile(),
                        msg.getMessage(),
                        msg.getStatus(),
                        msg.getReceivedTs(),
                        msg.getSentTs(),
                        msg.getUser().getAccountId(),
                        msg.getTelcoResponse()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void saveMessage(SendMsgDTO sendMsgDTO) {
        // Fetch user from database, or throw exception if not found
        User user = userRepository.findById(sendMsgDTO.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + sendMsgDTO.getAccountId()));

        // Create new message entity
        SendMsg sendMsg = new SendMsg();
        sendMsg.setMobile(sendMsgDTO.getMobile());
        sendMsg.setMessage(sendMsgDTO.getMessage());
        sendMsg.setStatus(MessageStatus.NEW); // Using Enum instead of string
        sendMsg.setReceivedTs(LocalDateTime.now());
        sendMsg.setUser(user);
        
        // If sentTs is null, use current timestamp
        sendMsg.setSentTs(sendMsgDTO.getSentTs() != null ? sendMsgDTO.getSentTs() : LocalDateTime.now());
        
        sendMsg.setTelcoResponse(sendMsgDTO.getTelcoResponse());

        
        sendMsgRepository.save(sendMsg);
    }
}
