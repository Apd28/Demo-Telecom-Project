package com.backend.threadapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "TelecomOperatorAPI", url = "http://localhost:2525")
public interface SendMsgFeignClient {

	@GetMapping("/telcom/smsc")
    String getTelecomOperatorResponse(@RequestParam Integer account_id, 
            @RequestParam Long mobile,
            @RequestParam String message);
}
