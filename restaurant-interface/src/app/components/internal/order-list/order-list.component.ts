import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { timeout } from 'rxjs';
import { CartItem } from 'src/app/services/cart-status/cart-status.service';


interface Order {
  id: number;
  status: string;
  items: CartItem[];
}

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  orders : Order[] = [];

  constructor(private http : HttpClient) { }

  ngOnInit(): void {
    this.refreshOrders();
  }

  refreshOrders(): void {
    this.http.get<Order[]>('http://localhost:3000/api/all-order-status')
      .subscribe(data => {
        let newOrders = (data as any).orders as Order[];

        let statusChanged = false;

        for(let i = 0; i < this.orders.length; i++) {
          if(newOrders[i].status !== this.orders[i].status) {
            statusChanged = true;
          }
        }

        if(statusChanged || newOrders.length > this.orders.length) {
          this.orders = newOrders;
        }

        setTimeout(() => {
          this.refreshOrders();
        }, 500);
      });
  }

  setStatus(id: number, status: string) {
    this.http.post("http://localhost:3000/api/modify-order-status", {
      id: id,
      status: status
    }).subscribe();
  }
}
