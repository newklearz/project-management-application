import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ApiService} from "../api.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router,
              private apiService: ApiService) {
  }

  ngOnInit(): void {
  }

  logout() {
    this.apiService.logOut();
    this.router.navigateByUrl('/');
  }
}
