package com.consumer.kafkaapi.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.consumer.kafkaapi.client.InternalDBClient;
import com.consumer.kafkaapi.dto.SendMsgDTO;
import com.customerapi.confluentkafka.SendMsg;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final InternalDBClient internalDBClient;

    @KafkaListener(topics = "send_sms_topic", groupId = "sms-consumer-group")
    public void consume(ConsumerRecord<String, SendMsg> record) {
        System.out.println("Consuming record: " + record);
        try {
            if (record.value() == null) {
                System.out.println("Received null message. Skipping...");
                return;
            }

            SendMsg sendMsg = record.value();
            System.out.println("Received Message: " + sendMsg);

            // Insert into Internal DB
            SendMsgDTO sendMsgDTO = new SendMsgDTO(
                sendMsg.getAccountId(),
                sendMsg.getMobile(),
                sendMsg.getMessage().toString(),
                LocalDateTime.now()
            );
            internalDBClient.insertSendMsg(sendMsgDTO);
            System.out.println("Received Message: inserted in DB - "
            +sendMsgDTO.getAccountId()+" - "+"Account ID - "+sendMsgDTO.getAccountId());
            

        } catch (Exception e) {
            System.err.println("Error processing Kafka message: " + e.getMessage());
            e.printStackTrace();
        }
    }}

