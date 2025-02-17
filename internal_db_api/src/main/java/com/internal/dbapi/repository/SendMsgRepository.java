package com.internal.dbapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internal.dbapi.model.SendMsg;

@Repository
public interface SendMsgRepository extends JpaRepository<SendMsg, Integer> {
}