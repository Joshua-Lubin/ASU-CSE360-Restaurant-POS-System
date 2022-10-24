import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
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

  orders? : Order[];

  constructor(private http : HttpClient) { }

  ngOnInit(): void {
    this.http.get<Order[]>('http://localhost:3000/api/all-order-status')
      .subscribe(data => {
        console.log(data);
        this.orders = data as Order[];
        console.log(this.orders);
      });
  }

}
