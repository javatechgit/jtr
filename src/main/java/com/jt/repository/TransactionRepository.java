package com.jt.repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jt.entity.TransactionEntity;


//@Repository
//public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	//TransactionEntity saveTransactionEntity(TransactionEntity transactionEntity);
	//Optional<TransactionEntity> findTransactionsById(Long customerId);
	//List<TransactionEntity> findTransactionByIdAndDateCreatedBetween(LocalDate startDate, LocalDate endDate);
//}

@Repository
//@Transactional
public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
    public List<TransactionEntity> findAllByCustomerId(Long customerId);

    public List<TransactionEntity> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp from,Timestamp to);
}
