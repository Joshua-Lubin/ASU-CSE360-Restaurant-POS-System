import { Component, OnInit } from '@angular/core';
import { CartStatusService } from 'src/app/services/cart-status/cart-status.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-asurite-checkout',
  templateUrl: './asurite-checkout.component.html',
  styleUrls: ['./asurite-checkout.component.css']
})
export class AsuriteCheckoutComponent implements OnInit {

  asuriteId = 0;

  constructor(private cartStatusService : CartStatusService, private http : HttpClient, private router : Router) { }

  ngOnInit(): void {
  }

  checkout(): void {
    this.http.post<any>('http://localhost:3000/api/create-asurite-order', { asuriteId: this.asuriteId, order: this.cartStatusService.cart })
      .subscribe(data => {
        this.router.navigate(['status', data.id]);
      });
  }

  onChange(event : any) {
    this.asuriteId = parseInt(event.target.value);
  }

}