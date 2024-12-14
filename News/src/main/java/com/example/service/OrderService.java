package com.example.service;

import java.util.Map;

import com.razorpay.RazorpayException;



public interface OrderService {
	
	public Map<String, Object> createOrder(double amount) throws RazorpayException;
	
	public void handlePaymentSuccess(String orderId, String paymentId, int userId);

}
