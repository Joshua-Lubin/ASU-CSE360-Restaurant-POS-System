import { Injectable, Input } from '@angular/core';

export interface CartItem {
  id: string;
  options: string[];
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartStatusService {

  private cart : CartItem[] = [];
  public totalPrice: number = 0;

  constructor() { }

  addItem(item : CartItem) : void {
    this.cart.push(item);
    this.totalPrice += item.quantity * 10;
  }

  getCart() : CartItem[] {
    return this.cart;
  }

  modifyItem(index: number, item : any) : void {
    if(item.quantity != undefined) {
      this.totalPrice += (item.quantity - this.cart[index].quantity) * 10;
    }
    this.cart[index] = {
      ...this.cart[index],
      ...item
    }
    console.log(this.totalPrice);
  }

  removeItem(index: number) {
    this.totalPrice = this.totalPrice - this.cart[index].quantity * 10;
    this.cart.splice(index, 1);
  }

  clearCart() : void {
    this.cart = [];
    this.totalPrice = 0;
  }
}