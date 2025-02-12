package com.example.ExpenseManagement.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class TransactionDTO {
    private String transactionId;
    private String transactionTitle;
    private String transactionCategory;
    private double transactionAmount;
    private Date transactionDate;
    private String transactionRemarks;
    private String transactionStatus;


}
