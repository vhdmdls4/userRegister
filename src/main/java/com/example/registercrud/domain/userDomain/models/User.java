package com.example.registercrud.domain.userDomain.models;

import com.example.registercrud.domain.userDomain.enums.PersonType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private BigDecimal balance;
    private PersonType personType;
    private String docNumber;
}
