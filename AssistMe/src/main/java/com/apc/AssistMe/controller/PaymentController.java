package com.apc.AssistMe.controller;

import com.apc.AssistMe.model.Payment;
import com.apc.AssistMe.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // GET ALL PAYMENTS
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // GET PAYMENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // MAKE PAYMENT
    @PostMapping
    public Payment makePayment(@RequestBody Payment payment) {
        payment.setStatus("COMPLETED");
        return paymentService.savePayment(payment);
    }

    // DELETE PAYMENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
