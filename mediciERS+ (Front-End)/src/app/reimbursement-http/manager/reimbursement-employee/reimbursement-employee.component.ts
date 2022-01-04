import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/users/login/user.model';
import { ReimbursementHttpService } from 'src/app/reimbursement-http/reimbursement-http.service';
import { Reimbursement } from 'src/app/reimbursement-http/reimbursement.model';
import { AuthService } from 'src/app/users/auth.service';

@Component({
  selector: 'app-reimbursement-employee',
  templateUrl: './reimbursement-employee.component.html',
  styleUrls: ['./reimbursement-employee.component.scss']
})
export class ReimbursementEmployeeComponent implements OnInit {

  flag: boolean = false;

  allUserRequests: Reimbursement[] = [];

  errorMsg: string = '';

  newRequest: Reimbursement = {
    rbId: 0,
    userId: 0,
    rbDate: "",
    rbAmount: 0,
    rbStatus: "",
    rbResolved: false,
    rbRemoved: false
  }

  userInfo: User = new User();

  constructor(private reimbursementHttpService: ReimbursementHttpService,
    private auth: AuthService,
    private router: Router) {

  }

  ngOnInit(): void {
    this.userInfo = this.auth.retrieveUser();
    this.loadRequests();
  }

  loadRequests() {
    this.newRequest.userId = this.userInfo.userId;
    this.reimbursementHttpService.getSpecificRequests(this.userInfo.userId).subscribe(
      (response) => {
        this.allUserRequests = response;
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


}