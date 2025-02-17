package com.customerapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customerapi.client.AccountClient;
import com.customerapi.service.KafkaProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/telcom")
@RequiredArgsConstructor
public class CustomerApiController {

    private final KafkaProducerService kafkaProducerService;
    private final AccountClient accountClient;

    @GetMapping("/sendmsg")
    public ResponseEntity<String> sendMessage(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam Long mobile,
            @RequestParam String message) {

        // Validate mobile number and message length
        if (mobile.toString().length() != 10 || message.isEmpty() || message.length() > 160) {
            return ResponseEntity.badRequest().body("STATUS: REJECTED~~ RESPONSE_CODE: FAILURE");
        }

        // Validate username & password via Internal DB API
        Integer accountId = accountClient.validateUser(username, password);
        if (accountId == null) {
            return ResponseEntity.badRequest().body("STATUS: REJECTED~~ RESPONSE_CODE: FAILURE");
        }

        // Send message to Kafka
        String ackId = kafkaProducerService.sendMessage(accountId, mobile, message);
        return ResponseEntity.ok("STATUS: ACCEPTED~~RESPONSE_CODE: SUCCESS~~" + ackId);
    }
}
