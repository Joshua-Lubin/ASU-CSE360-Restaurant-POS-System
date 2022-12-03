import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-internal-app',
  templateUrl: './internal-app.component.html',
  styleUrls: ['./internal-app.component.css']
})
export class InternalAppComponent implements OnInit {
  authenticated: boolean = false;
  password: string = "";

  constructor(private http : HttpClient) { }

  ngOnInit(): void {
  }

  login(): void {
    this.http.post<any>('http://localhost:3000/api/check-password', { password: this.password })
      .subscribe(() => {
        this.authenticated = true;
      });
  }

}
