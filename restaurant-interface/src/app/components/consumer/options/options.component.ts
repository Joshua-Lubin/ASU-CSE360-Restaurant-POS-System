import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MenuInfoService } from 'src/app/services/menu-info/menu-info.service';
import { CartStatusService, CartItem } from 'src/app/services/cart-status/cart-status.service';

@Component({
  selector: 'app-options',
  templateUrl: './options.component.html',
  styleUrls: ['./options.component.css']
})
export class OptionsComponent implements OnInit {

  id = '';
  optionList : string[][] = [];

  item : CartItem = {
    id: "",
    options: [],
    quantity: 1
  };

  constructor(private route : ActivatedRoute, private router : Router, private menuInfoService : MenuInfoService, private cartStatusService : CartStatusService) { }

  ngOnInit(): void {
    this.item.id = this.route.snapshot.paramMap.get('itemId') as string;
    this.optionList = Object.entries(this.menuInfoService.getOptionsList());
  }

  addToCart(): void {
    this.cartStatusService.addItem(this.item);
    console.log(this.cartStatusService.getCart());

    this.router.navigate(['cart']);
  }

  checkBoxEvent(event : any): void {
    const option = event.srcElement.name;
    const index = this.item.options.indexOf(option);

    if(index === -1) {
      this.item.options.push(option);
    }
    else {
      this.item.options.splice(index, 1);
    }
  }

  changeQuantityEvent(event : any): void {
    const value = parseInt(event.currentTarget.value);
    if(isNaN(value) || value < 1) {
      event.currentTarget.value = 1;
      this.item.quantity = 1;
    }
    else {
      console.log(value);
      this.item.quantity = value;
    }
  }
}
