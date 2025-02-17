package com.backend.threadapi.reository;

import java.util.List;

import org.apache.logging.log4j.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.threadapi.model.MessageStatus;
import com.backend.threadapi.model.SendMsg;

@Repository
public interface SmsRepository extends JpaRepository<SendMsg, Integer> {
	List<SendMsg> findByStatus(MessageStatus status);
}
