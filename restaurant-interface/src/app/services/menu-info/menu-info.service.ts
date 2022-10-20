import { Injectable } from '@angular/core';

interface MenuItem {
  name: string;
  price: number;
  imageUrl: string;
}

interface MenuItemList {
  [key : string] : MenuItem;
}

@Injectable({
  providedIn: 'root'
})
export class MenuInfoService {
  private menuItems : MenuItemList = {
    cheese: {
      name: "Cheese Pizza",
      price: 10,
      imageUrl: "/assets/cheese-pizza.png"
    },
    pepperoni: {
      name: "Pepperoni Pizza",
      price: 10,
      imageUrl: "/assets/pepperoni-pizza.png"
    },
    vegetable: {
      name: "Vegetable Pizza",
      price: 10,
      imageUrl: "/assets/vegetable-pizza.png"
    },
  }

  constructor() { }

  getMenuItemInfo(itemId : string) : MenuItem {
    return this.menuItems[itemId];
  }
}
