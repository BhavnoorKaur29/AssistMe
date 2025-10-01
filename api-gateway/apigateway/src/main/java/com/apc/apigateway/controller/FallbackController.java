// package com.apc.apigateway.controller;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class FallbackController {

//     @GetMapping("/fallback/products")
//     public String productServiceFallback() {
//         return "Product Service is currently unavailable. Please try again later.";
//     }

//     @GetMapping("/fallback/orders")
//     public String orderServiceFallback() {
//         return "Order Service is currently unavailable. Please try again later.";
//     }
// }


package com.apc.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/product")
    public ResponseEntity<String> productFallback() {
        return ResponseEntity.ok("Product Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/order")
    public ResponseEntity<String> orderFallback() {
        return ResponseEntity.ok("Order Service is currently unavailable. Please try again later.");
    }
}
