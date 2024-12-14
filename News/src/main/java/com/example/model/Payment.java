package com.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Payments_table")
public class Payment {

    @SuppressWarnings("deprecation")
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "Payment_ID")
    private String paymentId;

    @ManyToOne
    @JsonIgnore  // Prevent circular references (recursion)
    @JoinColumn(name = "Users_ID")
    private Users user;

    @Column(name = "Order_ID")
    private String orderId; 

    @Column(name = "Payment_Status")
    private String paymentStatus; //  'pending', 'completed', 'failed'

    @Column(name = "Amount_Paid")
    private BigDecimal amountPaid; 

    @Column(name = "Transaction_ID")
    private String transactionId; 

    @Column(name = "Payment_Date")
    private LocalDateTime paymentDate;


	public String getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}



	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}


	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", user=" + user.getUsersId() + ", orderId=" + orderId + ", paymentStatus="
				+ paymentStatus + ", amountPaid=" + amountPaid + ", transactionId=" + transactionId + ", paymentDate="
				+ paymentDate + "]";
	} 
}

