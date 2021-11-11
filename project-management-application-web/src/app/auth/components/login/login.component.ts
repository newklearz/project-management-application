import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css','./login.themes.component.css'],
})
export class LoginComponent implements OnInit {
  text: String ='password';

  constructor() { }

  ngOnInit(): void {
  }

  onShowPassword(){
    if(this.text==='password'){
      this.text='text';
    }
    else {
      this.text='password';
    }
  }
}
