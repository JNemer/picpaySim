package com.picpaySim.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName, lastName;

    // "CPF e a-mail devem ser únicos no sistema"

    @Column(unique = true)
    private String document, email;
    private String password;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
