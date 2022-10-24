import { Injectable } from '@angular/core';

export interface CartItem {
  id: string;
  options: string[];
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartStatusService {

  cart : CartItem[] = [];

  constructor() { }

  addItem(item : CartItem) : void {
    this.cart.push(item);
  }

  getCart() : CartItem[] {
    return this.cart;
  }
}