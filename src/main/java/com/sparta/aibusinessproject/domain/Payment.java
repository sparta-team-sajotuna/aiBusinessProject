package com.sparta.aibusinessproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "p_payment")
public class Payment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int payAmount;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatusEnum status;

    private String merchantUid;

    private String impUid;

    private String payMethod;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    private LocalDateTime deletedAt;
    private String deletedBy;
}
