package com.cac.tpcacfinal.repositories;

import com.cac.tpcacfinal.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
