import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../admin.service";
import {UsersDTO} from "../../../pma/api";
import {Observable, of} from 'rxjs'
import {flatMap} from "rxjs/operators";
import {UserServiceNotification} from "../user-service.notification";
import {CanComponentDeactivate} from "./can-deactivate-guard.service";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit, CanComponentDeactivate
{
  userForm = new FormGroup(
    {
    'userName': new FormControl('', Validators.required),
    'email': new FormControl('', Validators.required),
    'appUserRole': new FormControl('', Validators.required),
  });
  userDTO: UsersDTO;
  id: number;
  editMode = false;
  changesSaved = false;

  constructor(
    private userServiceNotification: UserServiceNotification,
    private route: ActivatedRoute,
    private adminService: AdminService,
    private router: Router)
  {
  }

  ngOnInit()
  {
    this.route.params.pipe(
      flatMap((params) => {
        this.id = +params['id'];
        if (params['id']) {
          this.editMode = true;
          return this.adminService.getUser(this.id);
        } else {
          return of(null);
        }
      }),
    ).subscribe((user: UsersDTO) => {
      this.initForm(user);
    });
  }

  onSubmit()
  {
    this.userDTO = this.userForm.value;
    if (this.editMode)
    {
      this.userDTO.id = this.id;
      this.adminService.updateUser(this.id, this.userDTO).subscribe(
        (user: UsersDTO) => this.userServiceNotification.dataChanged$.next(user)
      );
      this.changesSaved = true;
      this.router.navigate(['../../'], {relativeTo: this.route});
    } else if (!this.editMode)
    {
      this.adminService.addUser(this.userDTO).subscribe(
        (user: UsersDTO) => this.userServiceNotification.dataChanged$.next(user)
      );
      this.router.navigate(['../'], {relativeTo: this.route});
    }
    this.userForm.reset();
  }

  private initForm(data: UsersDTO)
  {
    this.userForm.patchValue(
      {
      'userName': data?.userName ?? '',
      'email': data?.email ?? '',
      'appUserRole': data?.appUserRole ?? ''
    })
  }

  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if ((this.userForm.value !== this.userDTO) &&
      !this.changesSaved) {
      console.log(this.changesSaved);
      return confirm('Do you want to discard the changes?');
    } else {
      return true;
    }
  }
}
