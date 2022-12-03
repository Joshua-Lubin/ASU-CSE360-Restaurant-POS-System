import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartStatusService } from 'src/app/services/cart-status/cart-status.service';

@Component({
  selector: 'app-credit-checkout',
  templateUrl: './credit-checkout.component.html',
  styleUrls: ['./credit-checkout.component.css']
})
export class CreditCheckoutComponent implements OnInit {

  asuriteId?: number;
  creditCardNumber?: number;
  expirationDate?: string;
  CVV?: number;

  constructor(private cartStatusService : CartStatusService, private http : HttpClient, private router : Router) { }

  ngOnInit(): void {
  }

  checkout(): void {
    this.http.post<any>('http://localhost:3000/api/create-credit-order', { asuriteId: this.asuriteId, order: this.cartStatusService.getCart(), creditCard: {cardNumber: this.creditCardNumber, cvv: this.CVV, expDate: this.expirationDate} })
      .subscribe(data => {
        this.cartStatusService.clearCart()
        this.router.navigate(['status', data.id]);
      },
      () => {
        this.router.navigate(['status', '0'])
      });
  }

  asu(event : any) {
    this.asuriteId = parseInt(event.target.value);
  }

  ccn(event : any) {
    this.creditCardNumber = parseInt(event.target.value);
  }

  exp(event : any) {
    this.expirationDate = event.target.value;
  }

  cvv(event : any) {
    this.CVV = parseInt(event.target.value);
  }

}
