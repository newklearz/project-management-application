import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./auth/components/login/login.component";
import {ErrorPageComponent} from "./error-page/error-page.component";
import {AuthGuard} from "./auth-guard.service";

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: '', redirectTo: 'login', pathMatch:'full'},
  { path:  'not-found', canActivate: [AuthGuard], component: ErrorPageComponent, data: {message: 'Page not found!'}},
  // {path: '**', redirectTo: '/not-found'}
];
@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule
{
}
