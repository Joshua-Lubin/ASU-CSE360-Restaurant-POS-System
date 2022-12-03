import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { CartStatusService, CartItem } from 'src/app/services/cart-status/cart-status.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart : CartItem[] = [];
  @Input() total : number = 0;

  constructor(private cartStatusService : CartStatusService, private router : Router) { }

  ngOnInit(): void {
    this.cart = this.cartStatusService.getCart();
    this.total = this.cartStatusService.totalPrice;
  }

  asuriteCheckout(): void {
    this.router.navigate(['asurite-checkout']);
  }

  cardCheckout(): void {
    this.router.navigate(['card-checkout']);
  }

  updateTotal() : void {
    this.total = this.cartStatusService.totalPrice;
  }

}
