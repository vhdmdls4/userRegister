package com.example.registercrud.domain.transactionDomain.controllers;

import com.example.registercrud.domain.exceptions.RegisterCrudExceptions;
import com.example.registercrud.domain.transactionDomain.dto.TransactionDTO;
import com.example.registercrud.domain.transactionDomain.models.Transaction;
import com.example.registercrud.domain.transactionDomain.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionControllers {
    @Autowired
    private TransactionServices transactionServices;
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        try {
            var allTransactions = transactionServices.findAllTransactionsService();
            return ResponseEntity.ok().body(allTransactions);
        }
        catch (RegisterCrudExceptions e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id){
        try {
            var transactionFound = transactionServices.findTransactionByIdService(id);
            return ResponseEntity.ok().body(transactionFound);
        } catch (RegisterCrudExceptions e){
            return ResponseEntity.badRequest().build();
        }
    }
//    @PostMapping
//    public ResponseEntity<TransactionDTO> createTransaction(TransactionDTO transactionDTO){
//        var transactionCreated = transactionServices.createTransactionService(transactionDTO);
//        return ResponseEntity.ok().body(transactionCreated);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> editTransaction(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO){
        try {
            var transactionEdited = transactionServices.editTransactionService(id, transactionDTO);
            return ResponseEntity.ok().body(transactionEdited);
        } catch (RegisterCrudExceptions e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id){
        try {
            transactionServices.deleteTransactionService(id);
            return ResponseEntity.ok("Transaction deletion was successful");
        } catch (RegisterCrudExceptions e){
            return ResponseEntity.badRequest().body("Failed to delete transaction: " + e.getMessage());
        }
    }

}
