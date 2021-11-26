import {Injectable} from "@angular/core";
import {Subject} from "rxjs";
import {UsersDTO} from "../../pma/api";

@Injectable()
export class UserServiceNotification {
  dataChanged$: Subject<UsersDTO> = new Subject<UsersDTO>();
}
