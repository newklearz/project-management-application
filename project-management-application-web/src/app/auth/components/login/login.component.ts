import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../../api.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css', './login.themes.component.css'],
})
export class LoginComponent implements OnInit
{
  credentials = {username: '', password: ''}
  showPassword = false;

  constructor(private apiService: ApiService,
              private http: HttpClient,
              private router: Router)
  {
  }

  ngOnInit() {
  }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword
  }

  login()
  {
    this.apiService.authenticate(this.credentials, () => {
    }).subscribe(resp => {
      this.router.navigateByUrl('users');
    }, (err => console.log(err)))
    return false;
  }
}
