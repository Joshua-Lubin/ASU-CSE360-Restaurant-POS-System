import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-status-prompt',
  templateUrl: './status-prompt.component.html',
  styleUrls: ['./status-prompt.component.css']
})
export class StatusPromptComponent implements OnInit {

  orderId: string = "";

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  submit(): void {
    this.router.navigate(['status', this.orderId]);
  }

}
