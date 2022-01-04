import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/users/auth.service';
import { User } from 'src/app/users/login/user.model';
import { ReimbursementHttpService } from '../../reimbursement-http.service';
import { Reimbursement } from '../../reimbursement.model';
import { ActivatedRoute } from '@angular/router';
import { UploadFileService } from 'src/app/upload-files/upload-file.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RequestImageModel } from 'src/app/upload-files/upload-files/upload-file.model';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-reimbursement-submit',
  templateUrl: './reimbursement-submit.component.html'
})
export class ReimbursementSubmitComponent implements OnInit {

  selectedFiles?: FileList;
  currentFile?: File;
  message = '';
  errorMsg = '';
  newRequestImage: RequestImageModel[] = [];

  flag: boolean = false;

  PendingRequests: Reimbursement[] = [];

  newRequest: Reimbursement = {
    rbId: 0,
    userId: 0,
    rbDate: "",
    rbAmount: 0,
    rbStatus: "",
    rbResolved: false,
    rbRemoved: false,
  }

  allUsers: User[] = [];
  userInfo: User = new User();
  fileInfos!: Observable<any>;

  constructor(private reimbursementHttpService: ReimbursementHttpService,
    private auth: AuthService,
    private activatedRoute: ActivatedRoute,
    private uploadService: UploadFileService,
    private sanitizer: DomSanitizer,
    private router: Router) { }

  ngOnInit(): void {
    this.userInfo = this.auth.retrieveUser();
    this.fileInfos = this.uploadService.getFiles();
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
        this.newRequest.rbId=response.rbId;
        this.upload();
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
      },
      (error)=> {
      }
    );
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

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }
  
}
