package com.example.registercrud.domain.transactionDomain.dto;

import com.example.registercrud.domain.userDomain.models.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TransactionDTO implements Serializable {
    private Long id;
    private BigDecimal amount;
    private User receiver;
    private User payer;
}
