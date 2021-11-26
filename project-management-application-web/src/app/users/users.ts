import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Data, Router} from '@angular/router';
import {ApiService} from "../api.service";
import {HttpClient} from "@angular/common/http";
import {UsersDTO} from "../../pma/api";
import {Observable} from "rxjs";
import {AdminService} from "./admin.service";
import {UserServiceNotification} from "./user-service.notification";

@Component({
  selector: 'app-error-page',
  templateUrl: './users.html',
  styleUrls: ['./users.css']
})
export class Users implements OnInit {
  errorMessage: string;
  users: Observable<UsersDTO[]>;
  toggleOff = "fa fa-fw text-secondary cursor-pointer fa-toggle-off";
  toggleOn = "fa fa-fw text-secondary cursor-pointer fa-toggle-on";

  constructor(private userServiceNotification: UserServiceNotification,
              private activatedRoute: ActivatedRoute,
              private appService: ApiService,
              private httpClient: HttpClient,
              private router: Router,
              private adminService: AdminService) {

  }

  ngOnInit() {
    this.userServiceNotification.dataChanged$.subscribe(() => {
      this.users = this.adminService.getUsers();
    })
    this.users = this.adminService.getUsers();
    this.activatedRoute.data.subscribe(
      (data: Data) => {
        this.errorMessage = data['message'];
      }
    );
  }

  trackByFn(index: number, item: UsersDTO) {
    return item.id;
  }


  onEditUser(index: number) {
    this.router.navigate(['edit', index], {relativeTo: this.activatedRoute});
  }

  onAddUser() {
    this.router.navigate(['edit'], {relativeTo: this.activatedRoute});
  }
}
