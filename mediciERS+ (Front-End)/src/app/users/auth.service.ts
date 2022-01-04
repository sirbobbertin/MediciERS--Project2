import { Injectable } from '@angular/core';
import { Reimbursement } from '../reimbursement-http/reimbursement.model';
import { User } from './login/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // this AuthService was created to keep track of 
      // 1) whether a user has logged in or not
      // 2) the user who has logged in and manipulate it in the session storage
  isLoggedIn: boolean = false;

  storeUser(user: User){
    sessionStorage.setItem("userData", JSON.stringify(user));
  }

  retrieveUser(): User{
    var data: any = sessionStorage.getItem("userData");
    return JSON.parse(data == null?'':data);
  }

  removeUser(){
    sessionStorage.removeItem("userData");
  }

  retrieveUserType(){
    var data: any = sessionStorage.getItem("userData");
    var user: User = JSON.parse(data == null?'':data);
    return user.userType;
  }

  constructor() { }
}
