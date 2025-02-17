package com.internal.dbapi.dto;

import java.time.LocalDateTime;

import com.internal.dbapi.model.MessageStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendMsgDTO {
    private Integer id;
    private Long mobile;
    private String message;
    private MessageStatus status;
    private LocalDateTime receivedTs;
    private LocalDateTime sentTs;
    private Integer accountId;  // Foreign Key
    private String telcoResponse;
}
