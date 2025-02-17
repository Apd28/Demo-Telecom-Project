package com.backend.threadapi.scheduler;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.backend.threadapi.client.SendMsgFeignClient;
import com.backend.threadapi.dto.SendMsgDTO;
import com.backend.threadapi.model.MessageStatus;
import com.backend.threadapi.model.SendMsg;
import com.backend.threadapi.reository.SmsRepository;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TelecomScheduler {
	private final SmsRepository smsRepository;
	private final SendMsgFeignClient sendMsgFeignClient; // Inject the Feign client
	private String response;

	@Scheduled(fixedRate = 1000)
	@Transactional
	public void processMessages() {
		List<SendMsg> pendingMessages = smsRepository.findByStatus(MessageStatus.NEW);
        if(!pendingMessages.isEmpty()) {
		for (SendMsg msg : pendingMessages) {
			msg.setStatus(MessageStatus.INPROGRESS);
			smsRepository.save(msg); // Save status update to "INPROCESS"

			try {
				// Create DTO for sending the message
				SendMsgDTO sendMsgDTO = new SendMsgDTO();
				sendMsgDTO.setAccountId(msg.getUser().getAccountId());
				sendMsgDTO.setMobile(msg.getMobile());
				sendMsgDTO.setMessage(msg.getMessage());

				// Send message via Feign client
				response = sendMsgFeignClient.getTelecomOperatorResponse(sendMsgDTO.getAccountId(), sendMsgDTO.getMobile(),
						sendMsgDTO.getMessage());

				// Assuming the response handling will be done via status updates
				msg.setTelcoResponse(response);
			} catch (Exception e) {
				msg.setTelcoResponse("STATUS: REJECTED~~RESPONSE_CODE: FAILURE");

			}

			// Update status and timestamp after processing
			msg.setStatus(MessageStatus.SENT);
			msg.setSentTs(LocalDateTime.now());
			smsRepository.save(msg); // Save the final state
		}}
	}
}
