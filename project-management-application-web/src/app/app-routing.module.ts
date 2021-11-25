import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./auth/components/login/login.component";
import {Users} from "./users/users";
import {AuthGuard} from "./auth-guard.service";
import {UserEditComponent} from "./users/user-edit/user-edit.component";
import {CanDeactivateGuard} from "./users/user-edit/can-deactivate-guard.service";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";

const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {
    path: 'users', component: Users, canActivate: [AuthGuard], children: [
      {path: 'edit', component: UserEditComponent},
      {path: 'edit/:id', component: UserEditComponent, canDeactivate: [CanDeactivateGuard]}
    ]
  },
  {path: 'not-found', component: PageNotFoundComponent},
  {path: '**', redirectTo: '/not-found'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
