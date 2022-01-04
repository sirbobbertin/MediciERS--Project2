import { Component, OnInit } from '@angular/core';
import { ReimbursementHttpService } from 'src/app/reimbursement-http/reimbursement-http.service';
import { AuthService } from 'src/app/users/auth.service';
import { Reimbursement } from 'src/app/reimbursement-http/reimbursement.model';
import { Router } from '@angular/router';
import { User } from 'src/app/users/login/user.model';
import { UploadFileService } from 'src/app/upload-files/upload-file.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-hello-employee',
  templateUrl: './hello-employee.component.html',
  styleUrls: ['./hello-employee.component.scss']
})
export class HelloEmployeeComponent implements OnInit {
  userFirstName(userFirstName: any) {
    throw new Error('Method not implemented.');
  }

  flag: boolean = false;
  date = new Date();
  PendingRequests: Reimbursement[] = [];

  selectedFiles?: FileList;
  currentFile?: File;
  message = '';
  errorMsg = '';

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
    private uploadService: UploadFileService,
    private router: Router) {

  }

  ngOnInit(): void {
    this.userInfo = this.auth.retrieveUser();
    this.loadRequests();
  }

  loadRequests() {
    this.reimbursementHttpService.getPendingRequests(this.userInfo.userId).subscribe(
      (response) => {
        this.PendingRequests = response;
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

  addReimbursement(){
    this.newRequest.userId=this.userInfo.userId;
    this.reimbursementHttpService.submitRequest(this.newRequest).subscribe(
      (response)=> {
        this.newRequest.rbId=response.rbId;
        this.upload();
        this.loadRequests();
      },
      (error) => {
      }
    )
  }

  upload(): void {
    this.errorMsg = '';
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      if (file) {
        this.currentFile = file;
        this.uploadService.upload(this.currentFile, this.newRequest.rbId).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              // console.log(Math.round(100 * event.loaded / event.total));
            } else if (event instanceof HttpResponse) {
              this.message = event.body.responseMessage;
            }
          },
          (err: any) => {
            this.currentFile = undefined;
          });
      }
      this.selectedFiles = undefined;
    }
  }

  getPendingRequests() {
    this.reimbursementHttpService.getPendingRequests(this.userInfo.userId).subscribe(
      (response) => {
        this.loadRequests();
      },
      (error) => {
      }
    )
  }

  deleteRequest(rbId: number) {
    this.reimbursementHttpService.deleteRequest(rbId).subscribe(
      (response) => {
        this.loadRequests();
      },
      (error) => {
      }
    )
  }

  goToEditComponent(rbId: any) {
    // console.log("logged: " + rbId);
    this.router.navigate(['reimbursement-edit'])
  }

  editReimbursement() {
    this.reimbursementHttpService.updateReimbursement(this.newRequest).subscribe(
      (response) => {
      },
      (error) => {
      }
    );
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }


}