import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Data, Router} from '@angular/router';
import {ApiService} from "../api.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.css']
})
export class ErrorPageComponent implements OnInit {
  errorMessage: string;

  constructor(private activatedRoute: ActivatedRoute, private appService: ApiService, private httpClient: HttpClient, private router: Router)
  {

  }

  ngOnInit()
  {
    this.activatedRoute.data.subscribe(
      (data: Data) => {
        this.errorMessage = data['message'];
      }
    );
  }

  logout()
  {
    this.appService.isAuthenticated = false;
    this.router.navigateByUrl('/');
  }
}
