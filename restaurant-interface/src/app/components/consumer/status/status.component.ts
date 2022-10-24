import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItem } from 'src/app/services/cart-status/cart-status.service';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {

  id : string | null;
  status? : string;
  items? : CartItem[];


  constructor(private route : ActivatedRoute, private http : HttpClient) {
    this.id = route.snapshot.paramMap.get("id");
  }

  ngOnInit(): void {
    this.http.get<any>('http://localhost:3000/api/order-status?id=' + this.id)
      .subscribe(data => {
        console.log(data);
        this.status = data.status;
        this.items = data.items as CartItem[];
      });
  }

}
