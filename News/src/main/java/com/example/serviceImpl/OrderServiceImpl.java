package com.example.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Payment;
import com.example.model.Users;
import com.example.repository.PaymentRepository;
import com.example.repository.UserRepository;
import com.example.service.OrderService;
import com.example.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    private RazorpayClient razorpayClient;


    @Override
    public Map<String, Object> createOrder(double amount) throws RazorpayException {
        long amountInPaise = (long) (amount * 100);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amountInPaise);
        orderRequest.put("currency", "INR");
        Order order = razorpayClient.orders.create(orderRequest);
        
        // Save order details in the Payment entity
        Payment payment = new Payment();
        payment.setOrderId(order.get("id"));
        payment.setAmountPaid(BigDecimal.valueOf(amountInPaise / 100.0)); // Save amount in rupees
        payment.setPaymentStatus("created");
        paymentRepository.save(payment);

        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("orderId", order.get("id"));
        orderDetails.put("amount", order.get("amount"));
        orderDetails.put("currency", order.get("currency"));
        return orderDetails;
    }

    @Override
    public void handlePaymentSuccess(String orderId, String paymentId, int usersId) {
        try {
        	
        	Map<String, String> attributes = new HashMap<>();
            attributes.put("razorpay_order_id", orderId);
            attributes.put("razorpay_payment_id", paymentId);
        	

            // Retrieve the payment details
            Payment payment = paymentRepository.findByOrderId(orderId);
            if (payment == null) {
                throw new RuntimeException("Payment details not found for order ID: " + orderId);
            }

            // Update payment status to completed
            payment.setTransactionId(paymentId);
            payment.setPaymentStatus("completed");
            payment.setPaymentDate(LocalDateTime.now());
            Users user = userService.getUserById(usersId);
            payment.setUser(user);
            paymentRepository.save(payment);

            // Update user's subscription status
            Users user1 = userService.getUserById(usersId);
            user1.setSubscriptionStatus("true");
            userService.addUser(user1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
