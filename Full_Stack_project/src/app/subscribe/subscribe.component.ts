import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../payment.service';
import { ActivatedRoute } from '@angular/router';

declare var Razorpay: any;

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrl: './subscribe.component.css'
})
export class SubscribeComponent implements OnInit {
  currUserId: any;

  constructor(private paymentService: PaymentService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.currUserId = this.activatedRoute.snapshot.params['userId'];
  }

  subscribe() {
    const amount = 1;

    this.paymentService.createOrder(amount).subscribe((order: any) => {

      const options: any = {
        key: "rzp_test_bSoPnY4HCvqOZC",
        amount: order.amount, // Amount in paise
        currency: order.currency,
        name: 'News Website',
        description: 'Subscription Payment',
        order_id: order.orderId,
        handler: (response: any) => {
          console.log(response);
          this.paymentService.verifyPayment(response.razorpay_order_id, response.razorpay_payment_id, this.currUserId).subscribe((result: any) => {
            alert("success " + result);
          },
            (error) => {
              alert('Payment verification failed: ' + error.message);
            }
          );
        }
      };
      const razorpay = new Razorpay(options);
      razorpay.open();

    })
  }
}
