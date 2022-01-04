import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../login/user.model';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.scss']
})
export class ManagerComponent implements OnInit {

  flag: boolean = false;

  allUsers: User[] = [];

  errorMsg: string = '';

  newUser: User = {
    userId: 0,
    userPassword: "",
    userFirstName: "",
    userLastName: "",
    userAddress: "",
    userContact: "",
    userType: "employee",
    userRemoved: false
  }

  constructor(private userService: UserService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getAllUsers().subscribe(
      (response) => {
        this.allUsers = response;
      },
      (error) => {
        this.errorMsg = 'There was some internal error! Please try again later!';
      }
    );
  }

  toggleAdd() {
    if (this.flag) {
      this.flag = false;
    } else {
      this.flag = true;
    }
  }

  register() {
    this.userService.register(this.newUser).subscribe(
      (response) => {
        this.loadUsers();
      },
      (error) => {
        this.errorMsg = 'There was some internal error! Please try again later!'
      }

    )
  }

  deleteUser(userId: number) {
    this.userService.deleteUser(userId).subscribe(
      (response) => {
        this.loadUsers();
      },
    )
  }

  editUser() {
    this.userService.updateUser(this.newUser).subscribe(
      (response) => {
        this.router.navigate(['user-edit']);
      },
      (error) => {
      }

    )
  }

}