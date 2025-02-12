package com.example.ExpenseManagement.services;

import com.example.ExpenseManagement.DTO.TransactionDTO;
import com.example.ExpenseManagement.entities.Transaction;
import com.example.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.example.ExpenseManagement.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDTO> getAllTransactions() {
        log.info("Fetching all transactions");
        return transactionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO getTransactionById(String transactionId) {
        log.info("Fetching transaction with ID: {}", transactionId);
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + transactionId));
        return convertToDTO(transaction);
    }

    @Transactional
    public TransactionDTO createTransaction(Transaction transaction) {
        log.info("Creating new transaction with title: {}", transaction.getTransactionTitle());
        Transaction savedTransaction = transactionRepository.save(transaction);
        return convertToDTO(savedTransaction);
    }

    @Transactional
    public TransactionDTO updateTransaction(String transactionId, Transaction transactionDetails) {
        log.info("Updating transaction with ID: {}", transactionId);
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + transactionId));

        transaction.setTransactionTitle(transactionDetails.getTransactionTitle());
        transaction.setTransactionAmount(transactionDetails.getTransactionAmount());
        transaction.setTransactionDate(transactionDetails.getTransactionDate());
        transaction.setTransactionCategory(transactionDetails.getTransactionCategory());
        transaction.setUpdated(new java.util.Date());

        Transaction updatedTransaction = transactionRepository.save(transaction);
        return convertToDTO(updatedTransaction);
    }

    @Transactional
    public void deleteTransaction(String transactionId) {
        log.info("Deleting transaction with ID: {}", transactionId);
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + transactionId));
        transactionRepository.delete(transaction);
    }

    public List<TransactionDTO> getTransactionsByProjectId(String projectId) {
        List<Transaction> transactions = transactionRepository.findByProject_ProjectId(projectId);

        return transactions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionId(transaction.getTransactionId());
        dto.setTransactionTitle(transaction.getTransactionTitle());
        dto.setTransactionCategory(transaction.getTransactionCategory());
        dto.setTransactionAmount(transaction.getTransactionAmount());
        dto.setTransactionDate(transaction.getTransactionDate());
        dto.setTransactionRemarks(transaction.getTransactionRemarks());
        dto.setTransactionStatus(transaction.getTransactionStatus());
        return dto;
    }
}