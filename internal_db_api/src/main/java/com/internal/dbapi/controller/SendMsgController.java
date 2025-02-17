package com.internal.dbapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internal.dbapi.dto.SendMsgDTO;
import com.internal.dbapi.service.SendMsgService;

@RestController
@RequestMapping("api/send-msg")
public class SendMsgController {

    @Autowired
    private SendMsgService sendMsgService;

    @GetMapping
    public List<SendMsgDTO> getAllMessages() {
        return sendMsgService.getAllMessages();
    }

    @PostMapping
    public void sendMessage(@RequestBody SendMsgDTO sendMsgDTO) {
        sendMsgService.saveMessage(sendMsgDTO);
    }
}