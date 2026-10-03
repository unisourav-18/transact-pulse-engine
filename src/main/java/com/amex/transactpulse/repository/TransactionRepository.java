package com.amex.transactpulse.repository;

import com.amex.transactpulse.model.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionRecord, Long> {
    boolean existsByTransactionId(String transactionId);
    Optional<TransactionRecord> findByTransactionId(String transactionId);
}