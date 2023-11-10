package com.cac.tpcacfinal.repositories;

import com.cac.tpcacfinal.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
