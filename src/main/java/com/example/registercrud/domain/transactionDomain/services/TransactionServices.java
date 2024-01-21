package com.example.registercrud.domain.transactionDomain.services;

import com.example.registercrud.domain.exceptions.RegisterCrudExceptions;
import com.example.registercrud.domain.transactionDomain.dto.TransactionDTO;
import com.example.registercrud.domain.transactionDomain.mapper.TransactionMapper;
import com.example.registercrud.domain.transactionDomain.models.Transaction;
import com.example.registercrud.domain.transactionDomain.repositories.TransactionRepository;
import com.example.registercrud.domain.userDomain.services.implement.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServices {
    @Autowired
    private TransactionRepository transactionRepository;
    public List<TransactionDTO> findAllTransactionsService() throws RegisterCrudExceptions {
        List<Transaction> allTransactions = transactionRepository.findAll();
        if(allTransactions.isEmpty()){
            throw new RegisterCrudExceptions("No transactions found");
        }
        var allTransactionsMappedToDTO = allTransactions.stream().map(TransactionMapper::toTransactionDTO).toList();
        return allTransactionsMappedToDTO;
    }

    public TransactionDTO findTransactionByIdService(Long id) throws RegisterCrudExceptions{
        var transaction = transactionRepository.findById(id);
        if(transaction.isEmpty()){
            throw new RegisterCrudExceptions("No transaction found with this id");
        }
        return TransactionMapper.toTransactionDTO(transaction.get());
    }

//    public TransactionDTO createTransactionService(TransactionDTO transactionDTO) throws RegisterCrudExceptions {
//       var userPayer = userServices.findUserByIdService(transactionDTO.getPayer().getId());
//       if(userPayer==null){
//           throw new RegisterCrudExceptions("Payer not found");
//       }
//
//    }

    public TransactionDTO editTransactionService(Long id, TransactionDTO transactionDTO) throws RegisterCrudExceptions{
        var transaction = this.findTransactionByIdService(id);
        var transactionToEdit = TransactionMapper.toTransactionEntity(transaction);
        transactionToEdit.setAmount(transactionDTO.getAmount());
        transactionToEdit.setPayer(transactionDTO.getPayer());
        transactionToEdit.setReceiver(transactionDTO.getReceiver());
        var transactionEdited = transactionRepository.save(transactionToEdit);
        return TransactionMapper.toTransactionDTO(transactionEdited);
    }
    public void deleteTransactionService(Long id) throws RegisterCrudExceptions{
        try {
            var transaction = this.findTransactionByIdService(id);
            transactionRepository.deleteById(transaction.getId());
        } catch (RegisterCrudExceptions e){
            throw new RegisterCrudExceptions("Error in delete transaction, try again later");
        }
    }
}
