package com.amex.transactpulse.controller;

import com.amex.transactpulse.dto.TransactionRequest;
import com.amex.transactpulse.model.TransactionRecord;
import com.amex.transactpulse.repository.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/ingest")
    public ResponseEntity<?> ingest(@Valid @RequestBody TransactionRequest req) {
        if (repository.existsByTransactionId(req.transactionId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "error", "DUPLICATE_TRANSACTION",
                "message", "Transaction ID already ingested: " + req.transactionId()
            ));
        }

        TransactionRecord record = new TransactionRecord(
            req.transactionId(),
            req.accountId(),
            req.amount(),
            req.currency().toUpperCase(),
            "SETTLED",
            Instant.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(record));
    }

    @GetMapping
    public List<TransactionRecord> getAllTransactions() {
        return repository.findAll();
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "TransactPulse Engine");
    }
}