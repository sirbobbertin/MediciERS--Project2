import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/users/auth.service';
import { ReimbursementHttpService } from '../../reimbursement-http.service';
import { Reimbursement } from '../../reimbursement.model';


@Component({
  selector: 'app-reimbursement-resolved-emp',
  templateUrl: './reimbursement-resolved-emp.component.html',
  styleUrls: ['./reimbursement-resolved-emp.component.scss']
})
export class ReimbursementResolvedEmpComponent implements OnInit {

  flag: boolean = false;

  ResolvedRequests: Reimbursement[] = [];

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

  constructor(private reimbursementHttpService: ReimbursementHttpService,
              private auth: AuthService,
              private router: Router) { 

    }

  ngOnInit(): void {
    this.loadRequests(this.auth.retrieveUser().userId); // HAVE TO HARDCODE PASS IN USERID (NEED NEW SOLUTION)
  }

  loadRequests(userId: number){
    this.reimbursementHttpService.getResolvedRequests(userId).subscribe(
      (response)=> {
        this.ResolvedRequests = response;
      },
      (error)=>{
        this.errorMsg = 'There was some internal error! Please try again later!';
      }  
    );
  }

  getResolvedRequests(userId: number){
    this.reimbursementHttpService.getResolvedRequests(userId).subscribe(
      (response) => {
        this.loadRequests(userId);
      },
      (error) => {
      }
    )
  }

}