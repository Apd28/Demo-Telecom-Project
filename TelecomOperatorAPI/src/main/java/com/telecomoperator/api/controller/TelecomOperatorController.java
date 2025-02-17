package com.telecomoperator.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/telcom")
public class TelecomOperatorController {

    @GetMapping("/smsc")
    public ResponseEntity<String> sendSms(
            @RequestParam Integer account_id, 
            @RequestParam Long mobile,
            @RequestParam String message) {

        // Validate the mobile number (10 digits)
        if (mobile.toString().length() != 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("STATUS: REJECTED~~RESPONSE_CODE: FAILURE");
        }

        // Validate if message is empty or exceeds 160 characters
        if (message.isEmpty() || message.length() > 160) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("STATUS: REJECTED~~RESPONSE_CODE: FAILURE");
        }

        // Validate if account_id is provided (non-null)
        if (account_id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("STATUS: REJECTED~~RESPONSE_CODE: FAILURE");
        }

        // Generate the unique acknowledgement ID (UUID)
        String ackId = UUID.randomUUID().toString();

        // Return the success response
        return ResponseEntity.ok("STATUS: ACCEPTED~~RESPONSE_CODE: SUCCESS~~" + ackId);
    }
}

