package com.internal.dbapi.model;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "send_msg")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendMsg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mobile", nullable = false)
    private Long  mobile;

    @Column(name = "message", nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    private MessageStatus status; 

    @Column(name = "received_ts", nullable = false, updatable = false)
    private LocalDateTime receivedTs;

    @Column(name = "sent_ts")
    private LocalDateTime sentTs;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private User user;  // Many messages can belong to one user

    @Column(name = "telco_response")
    private String telcoResponse;
}
