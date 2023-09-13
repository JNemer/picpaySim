package com.picpaySim.domain.operation;

import com.picpaySim.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="operations")
@Table(name ="operations")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private BigDecimal amount;

    // "um usuário pode ter várias transações, mas uma transação apenas um sender e um receiver"

    @ManyToOne
    @JoinColumn(name ="sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name="receiver_id")
    private User receiver;
    private LocalDateTime timestamp;
}
