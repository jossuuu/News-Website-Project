package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class RazorpayConfig {
	
	 @Value("${razorpay.keyId}")
	    private String keyId;
	    
	    @Value("${razorpay.keySecret}")
	    private String keySecret;
	    
	    @Bean
	    public RazorpayClient razorpayClient() throws RazorpayException {
	        return new RazorpayClient(keyId, keySecret);
	    }

}