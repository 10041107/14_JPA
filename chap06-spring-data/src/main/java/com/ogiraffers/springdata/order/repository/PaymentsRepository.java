package com.ogiraffers.springdata.order.repository;

import com.ogiraffers.springdata.order.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
}
