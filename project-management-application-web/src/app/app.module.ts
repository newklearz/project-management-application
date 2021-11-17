import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {LoginComponent} from "./auth/components/login/login.component";
import {AppRoutingModule} from "./app-routing.module";
import {ErrorPageComponent} from "./error-page/error-page.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ErrorPageComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
