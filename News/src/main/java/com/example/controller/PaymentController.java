package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.OrderService;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:4200/")
public class PaymentController {

    @Autowired
    private OrderService orderService;

    // Endpoint to create an order
    @PostMapping("/create-order/{amount}")
    public Map<String, Object> createOrder(@PathVariable double amount) {
        try {
            return orderService.createOrder(amount);
        } catch (RazorpayException e) {
            throw new RuntimeException("Error while creating Razorpay order: " + e.getMessage());
        }
    }

    // Endpoint to handle payment success
    @PostMapping("/payment-success")
    public String handlePaymentSuccess(
            @RequestParam String orderId,
            @RequestParam String paymentId,
            @RequestParam int userId) {
        try {
            orderService.handlePaymentSuccess(orderId, paymentId, userId);
            return "Payment successful and subscription updated!";
        } catch (Exception e) {
            return "Error while handling payment success: " + e.getMessage();
        }
    }
}
