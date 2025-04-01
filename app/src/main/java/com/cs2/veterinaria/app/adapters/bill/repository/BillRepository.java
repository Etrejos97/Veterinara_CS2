package com.cs2.veterinaria.app.adapters.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cs2.veterinaria.app.adapters.bill.entity.BillEntity;

public interface BillRepository extends JpaRepository<BillEntity, Long> {
}
