import {Injectable} from "@angular/core";
import {UsersDTO, UserService} from "../../pma/api";
import {Observable} from "rxjs";


@Injectable({providedIn: 'root'})
export class AdminService {

  constructor(private userService: UserService) {
  }

  getUsers() {
    return this.userService.getUsers();
  }

  getUser(id: number): Observable<UsersDTO> {
    return this.userService.getUser(id);
  }

  addUser(user: UsersDTO): Observable<UsersDTO>{
    return this.userService.createUser(user);
  }

  deactivateUser(id: number){
    return this.userService.deactivateUser(id);
  }

  updateUser(id: number, userDTO: UsersDTO): Observable<UsersDTO>{
    return this.userService.updateUser(id, userDTO);
  }
}
