package com.customerapi.service;

import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.customerapi.confluentkafka.SendMsg;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

	 private final KafkaTemplate<String, SendMsg> kafkaTemplate;
	    private static final String TOPIC = "send_sms_topic";

	    public String sendMessage(int accountId, long mobile, String message) {
	        String ackId = UUID.randomUUID().toString();

	        SendMsg sendMsg = SendMsg.newBuilder()
	                .setAckId(ackId)
	                .setAccountId(accountId)
	                .setMobile(mobile)
	                .setMessage(message)
	                .build();

	        ProducerRecord<String, SendMsg> record = new ProducerRecord<>(TOPIC, ackId, sendMsg);
	        kafkaTemplate.send(record);

	        return ackId; // Return Acknowledgement ID
}}
