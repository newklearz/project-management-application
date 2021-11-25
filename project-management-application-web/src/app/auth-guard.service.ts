import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {ApiService} from "./api.service";

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate, CanActivateChild {
  constructor(private apiService: ApiService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.apiService.isUserLoggedIn()) {
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }

  canActivateChild(route: ActivatedRouteSnapshot,
                   state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.canActivate(route, state);
  }
}
