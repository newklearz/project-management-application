import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css','./login.themes.component.css'],
})
export class LoginComponent implements OnInit {
  text: String ='password';
  showPassword = false;

  constructor() { }

  ngOnInit(): void {
  }

  togglePasswordVisibility(){
    this.showPassword = !this.showPassword
  }
}
