package com.consumer.kafkaapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.consumer.kafkaapi.dto.SendMsgDTO;



@FeignClient(name = "internal-db-api", url = "http://localhost:1010")
public interface InternalDBClient {

	@PostMapping("/api/send-msg")
    void insertSendMsg(@RequestBody SendMsgDTO sendMsgDTO);
}
