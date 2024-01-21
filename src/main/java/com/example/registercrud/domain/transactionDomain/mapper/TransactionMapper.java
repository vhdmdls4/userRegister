package com.example.registercrud.domain.transactionDomain.mapper;

import com.example.registercrud.domain.transactionDomain.dto.TransactionDTO;
import com.example.registercrud.domain.transactionDomain.models.Transaction;

public class TransactionMapper {
    public static Transaction toTransactionEntity(TransactionDTO transactionDTO){
        return Transaction.builder()
                .amount(transactionDTO.getAmount())
                .payer(transactionDTO.getPayer())
                .receiver(transactionDTO.getReceiver())
                .build();
    }
    public static TransactionDTO toTransactionDTO(Transaction transaction){
        return TransactionDTO.builder()
                .amount(transaction.getAmount())
                .payer(transaction.getPayer())
                .receiver(transaction.getReceiver())
                .id(transaction.getId())
                .build();
    }
}
