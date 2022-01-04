import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/users/auth.service';
import { User } from 'src/app/users/login/user.model';
import { ReimbursementHttpService } from '../../reimbursement-http.service';
import { Reimbursement } from '../../reimbursement.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-reimbursement-edit',
  templateUrl: './reimbursement-edit.component.html'
})
export class ReimbursementEditComponent implements OnInit {
  
  flag: boolean = false;

  PendingRequests: Reimbursement[] = [];

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
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.userInfo = this.auth.retrieveUser();
    this.loadRequests();
  }

  loadRequests(){
    this.reimbursementHttpService.getPendingRequests(this.userInfo.userId).subscribe(
      (response)=> {
        this.PendingRequests = response;
      },
      (error)=>{
        this.errorMsg = 'There was some internal error! Please try again later!';
      }  
    );
  }

  toggleAdd(){
    if(this.flag){
      this.flag = false;
    }else{
      this.flag = true;
    }
  }

  addReimbursement(){
    this.newRequest.userId=this.userInfo.userId;
    this.reimbursementHttpService.submitRequest(this.newRequest).subscribe(
      (response)=> {
        this.loadRequests();
      },
      (error) => {
      }
    )
  }

  getPendingRequests(){
    this.reimbursementHttpService.getPendingRequests(this.userInfo.userId).subscribe(
      (response)=> {
        this.loadRequests();
      },
      (error) => {
      }
    )
  }

  deleteRequest(rbId: number){
    this.reimbursementHttpService.deleteRequest(rbId).subscribe(
      (response) => {
        this.loadRequests();
      },
      (error) => {
      }
    )
  }

  goToEditComponent(rbId: any){
    this.router.navigate(['reimbursement-edit'])
  }

  editReimbursement(){
    this.reimbursementHttpService.updateReimbursement(this.newRequest).subscribe(
      (response) => {
        this.router.navigate(['reimbursement-edit']);
      },
      (error)=> {
      }
    );
  }


}