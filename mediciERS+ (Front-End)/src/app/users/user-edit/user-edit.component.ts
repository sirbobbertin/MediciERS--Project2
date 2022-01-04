import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html'
})
export class UserEditComponent implements OnInit {

  updateUser = {
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
    private auth: AuthService,
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.updateUser = this.auth.retrieveUser();

  }

  editUser() {
    this.userService.updateUser(this.updateUser).subscribe(
      (response) => {
        this.router.navigate(['user-edit']);
      },
      (error) => {
      }
    );
  }

}