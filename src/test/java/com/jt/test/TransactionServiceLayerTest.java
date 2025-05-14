package com.jt.test;

import static org.mockito.ArgumentMatchers.any;

import java.sql.Timestamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.jt.entity.TransactionEntity;
import com.jt.repository.TransactionRepository;
import com.jt.service.TransactionServiceImpl;

@SpringBootTest
public class TransactionServiceLayerTest {

	@InjectMocks 
	private TransactionServiceImpl transactionService;
	 
	@Mock
	private TransactionRepository transactionRepository;
	
	@Test
	 public void test_saveTransactionSuccess() {
		 TransactionEntity mockTransaction = TransactionEntity.builder()
					.customerId(1L)
					.transactionDate(Timestamp.valueOf("2025-05-06 00:00:00.0"))
	    			.transactionAmount(70.0).build();

		 Mockito.when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(mockTransaction);

		 TransactionEntity savedTransaction = transactionRepository.save(mockTransaction);
		 Assertions.assertNotNull(savedTransaction);
	     Assertions.assertTrue(savedTransaction.getTransactionDate().equals(Timestamp.valueOf("2025-05-06 00:00:00.0")));
	     Assertions.assertEquals(savedTransaction.getTransactionAmount(),70.0);
	 }
}
