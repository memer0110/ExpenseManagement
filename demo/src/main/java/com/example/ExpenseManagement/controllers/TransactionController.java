package com.example.ExpenseManagement.controllers;

import com.example.ExpenseManagement.DTO.TransactionDTO;
import com.example.ExpenseManagement.entities.Transaction;
import com.example.ExpenseManagement.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        log.info("GET request to fetch all transactions");
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable String id) {
        log.info("GET request to fetch transaction with ID: {}", id);
        TransactionDTO transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody Transaction transaction) {
        log.info("POST request to create a new transaction");
        TransactionDTO createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable String id, @RequestBody Transaction transactionDetails) {
        log.info("PUT request to update transaction with ID: {}", id);
        TransactionDTO updatedTransaction = transactionService.updateTransaction(id, transactionDetails);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable String id) {
        log.info("DELETE request to remove transaction with ID: {}", id);
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
