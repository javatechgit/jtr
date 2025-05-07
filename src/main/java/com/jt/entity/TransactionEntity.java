package com.jt.entity;

import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "RETAIL_TRANSACTIONS")
public class TransactionEntity {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

	@NotNull(message = "customer id is required")
    @Column(name="CUSTOMER_ID")
    private Long customerId;

	@NotNull(message = "transaction date is required")
    @Column(name = "TRANSACTION_DATE")
    private Timestamp transactionDate;

	@NotNull(message = "transaction amount is required")
	@Min(value = 1,message = "transaction amount can not be lower than 1")
    @Column(name = "TRANSACTION_AMOUNT")
    private double transactionAmount;

}
