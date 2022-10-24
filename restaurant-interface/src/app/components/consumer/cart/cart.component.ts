import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartStatusService, CartItem } from 'src/app/services/cart-status/cart-status.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart : CartItem[] = [];
  total : number = 0;

  constructor(private cartStatusServer : CartStatusService, private router : Router) { }

  ngOnInit(): void {
    this.cart = this.cartStatusServer.getCart();
    for(let i = 0; i  < this.cart.length; i++) {
      this.total += this.cart[i].quantity * 10;
    }
  }

  asuriteCheckout(): void {
    this.router.navigate(['asurite-checkout']);
  }

  cardCheckout(): void {
    this.router.navigate(['card-checkout']);
  }

}
