import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private createOrderURL = "http://localhost:8080/api/payment";
  private paymentSuccessURL = "http://localhost:8080/api/payment/payment-success";

  constructor(private http: HttpClient) { }

  // Create order
  createOrder(amount: number) {
    return this.http.post(`${this.createOrderURL}/create-order/${amount}`, {});
  }

  // Handle payment success
  verifyPayment(orderId: string, paymentId: string, userId: number) {
    const params = `?orderId=${orderId}&paymentId=${paymentId}&userId=${userId}`;
    return this.http.post(`${this.paymentSuccessURL}${params}`, null, {
      responseType: 'text',
    });
  }
}
