import { Injectable } from '@angular/core';

export interface MenuItem {
  name: string;
  price: number;
  imageUrl: string;
}

interface MenuItemList {
  [key : string] : MenuItem;
}

export interface OptionList {
  [key : string] : string;
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

  private optionList : OptionList = {
    mushrooms: "Mushrooms",
    onions: "Onions",
    olives: "Olives",
    extraCheese: "Extra Cheese"
  }

  constructor() { }

  getMenuItemInfo(itemId : string) : MenuItem {
    return this.menuItems[itemId];
  }

  getOptionsList() : OptionList {
    return this.optionList
  }
}
