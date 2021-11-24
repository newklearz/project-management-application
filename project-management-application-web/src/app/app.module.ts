import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginComponent} from "./auth/components/login/login.component";
import {AppRoutingModule} from "./app-routing.module";
import {Users} from "./users/users";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from "./header/header.component";
import {ApiModule, UserService} from "../pma/api";
import {UserEditComponent} from './users/user-edit/user-edit.component';
import {CanDeactivateGuard} from "./users/user-edit/can-deactivate-guard.service";
import {UserServiceNotification} from "./users/user-service.notification";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    Users,
    PageNotFoundComponent,
    HeaderComponent,
    UserEditComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ApiModule,
    ReactiveFormsModule
  ],
  providers: [UserService, UserServiceNotification, CanDeactivateGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
