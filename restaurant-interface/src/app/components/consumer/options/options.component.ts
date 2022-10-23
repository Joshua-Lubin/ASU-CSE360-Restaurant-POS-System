import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MenuInfoService } from 'src/app/services/menu-info/menu-info.service';

interface CartItem {
  id: string;
  options: string[];
  quantity: number;
}

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

  constructor(private route : ActivatedRoute, private menuInfoService : MenuInfoService) { }

  ngOnInit(): void {
    this.item.id = this.route.snapshot.paramMap.get('itemId') as string;
    this.optionList = Object.entries(this.menuInfoService.getOptionsList());
  }

  addToCart(): void {
    console.log("clicked");
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

    console.log(this.item);
  }

  changeQuantityEvent(event : any): void {
    console.log(event);
  }

}
