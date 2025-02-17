package com.consumer.kafkaapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMsgDTO {
    private Integer accountId;
    private Long mobile;
    private String message;
    private LocalDateTime receivedTs;
}
