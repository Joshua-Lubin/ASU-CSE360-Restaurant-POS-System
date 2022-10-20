import { Component, Input, OnInit } from '@angular/core';
import { MenuInfoService } from 'src/app/services/menu-info/menu-info.service';

@Component({
  selector: 'app-menu-item',
  templateUrl: './menu-item.component.html',
  styleUrls: ['./menu-item.component.css']
})
export class MenuItemComponent implements OnInit {

  @Input() itemId='';

  name='';
  price=0;
  imageUrl='';

  constructor(private menuInfoService : MenuInfoService) { }

  ngOnInit(): void {
    let menuItem = this.menuInfoService.getMenuItemInfo(this.itemId);

    this.name = menuItem.name;
    this.price = menuItem.price;
    this.imageUrl = menuItem.imageUrl;
  }

}
