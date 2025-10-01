package com.apc.AssistMe.repository;

import com.apc.AssistMe.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
