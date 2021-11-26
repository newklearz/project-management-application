import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map} from "rxjs/operators";

@Injectable({providedIn: 'root'})
export class ApiService {

  constructor(private httpClient: HttpClient) {
  }

  authenticate(credentials, callback) {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});
    return this.httpClient.get('http://localhost:8080/users', {headers: headers}).pipe(
      map(userData => {
        sessionStorage.setItem('username', credentials.username);
        let authString = 'Basic ' + btoa(credentials.username + ':' + credentials.password);
        sessionStorage.setItem('basicauth', authString);
        return userData;
      })
    );
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username');
    console.log(!(user === null));
    return !(user === null);
  }

  logOut() {
    sessionStorage.clear();
  }
}
