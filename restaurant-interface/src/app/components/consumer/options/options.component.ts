import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MenuInfoService } from 'src/app/services/menu-info/menu-info.service';

@Component({
  selector: 'app-options',
  templateUrl: './options.component.html',
  styleUrls: ['./options.component.css']
})
export class OptionsComponent implements OnInit {

  itemId = '';
  optionList : string[][] = [];

  constructor(private route : ActivatedRoute, private menuInfoService : MenuInfoService) { }

  ngOnInit(): void {
    this.itemId = this.route.snapshot.paramMap.get('itemId') as string;
    this.optionList = Object.entries(this.menuInfoService.getOptionsList());
  }

}
