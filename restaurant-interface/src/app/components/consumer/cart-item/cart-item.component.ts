import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CartItem, CartStatusService } from 'src/app/services/cart-status/cart-status.service';
import { MenuInfoService, MenuItem,  } from 'src/app/services/menu-info/menu-info.service';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {

  @Input() item! : CartItem;
  menuItem!: MenuItem;
  optionsList!: string[];
  @Input() index!: number;
  @Input() statusOnly!: boolean;
  @Output("updateTotal") updateTotal: EventEmitter<any> = new EventEmitter()

  constructor(private menuInfoService : MenuInfoService, private cartStatusService : CartStatusService) { }

  ngOnInit(): void {
    this.menuItem = this.menuInfoService.getMenuItemInfo(this.item.id);
    const optionsList = this.menuInfoService.getOptionsList();
    this.optionsList = this.item.options.map((option) => (optionsList[option]));
  }

  updateQuantity(event: any): void {
    if(event.target.value > 0) {
      this.cartStatusService.modifyItem(this.index, {quantity: event.target.value});
    }
    else {
      event.target.value = 1;
    }
    this.updateTotal.emit();
  }

  removeItem(): void {
    this.cartStatusService.removeItem(this.index);
    this.updateTotal.emit();
  }
}
