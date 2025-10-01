package com.apc.AssistMe.repository;

import com.apc.AssistMe.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
