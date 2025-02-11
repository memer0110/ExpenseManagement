package com.example.ExpenseManagement.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "gst_inclusion")
    private String gstInclusion;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "gst_amount")
    private double gstAmount;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "item_qty")
    private int itemQty;

    @Column(name = "item_rate")
    private double itemRate;

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @Column(name = "transaction_category")
    private String transactionCategory;

    @Column(name = "transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(name = "transactiongstpercentage")
    private double transactionGstPercentage;

    @Column(name = "transaction_item")
    private String transactionItem;

    @Column(name = "transaction_remarks")
    private String transactionRemarks;

    @Column(name = "transaction_status")
    private String transactionStatus;

    @Column(name = "transaction_title")
    private String transactionTitle;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "unit")
    private String unit;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "ref_transaction_id")
    private Transaction refTransaction;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "refTransaction")
    private List<Transaction> childTransactions; // For the self-referencing relationship

    @OneToMany(mappedBy = "transaction")
    private List<Approval> approvals;


}
