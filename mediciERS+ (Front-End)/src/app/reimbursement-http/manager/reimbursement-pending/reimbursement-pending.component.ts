import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/users/login/user.model';
import { ReimbursementHttpService } from 'src/app/reimbursement-http/reimbursement-http.service';
import { Reimbursement } from 'src/app/reimbursement-http/reimbursement.model';
import { AuthService } from 'src/app/users/auth.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { UploadFileService } from 'src/app/upload-files/upload-file.service';
import { RequestImageModel } from 'src/app/upload-files/upload-files/upload-file.model';
import { Observable } from 'rxjs';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-reimbursement-pending',
  templateUrl: './reimbursement-pending.component.html',
  styleUrls: ['./reimbursement-pending.component.scss']
})
export class ReimbursementPendingComponent implements OnInit {

  flag: boolean = false;

  allPendingRequests: Reimbursement[] = [];
  allUserRequests: Reimbursement[] = [];
  newRequestImage: RequestImageModel[] = [];

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

  allUsers: User[] = [];
  userInfo: User = new User();
  fileInfos!: Observable<any>;

  constructor(private reimbursementHttpService: ReimbursementHttpService,
    private auth: AuthService,
    private sanitizer: DomSanitizer,
    private activatedRoute: ActivatedRoute,
    private uploadService: UploadFileService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.userInfo = this.auth.retrieveUser();
    this.fileInfos = this.uploadService.getFiles();
    this.loadRequests();
    this.loadRequests2();
  }

  loadRequests() {
    this.reimbursementHttpService.getAllPendingRequests().subscribe(
      (response) => {
        this.newRequestImage = response;
        this.newRequestImage.forEach((reimbursement, index) => {
          const mediaType = 'application/image';
          this.reimbursementHttpService.getFilesByRb(reimbursement.rbId).subscribe(value => {
            // console.log(value);
            const blob = new Blob([value], { type: mediaType });
            const unsafeImg = URL.createObjectURL(blob);
            this.newRequestImage[index].imageBlob = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
          }, error1 => {
            this.newRequestImage[index].imageBlob = "";
          });
        })
        // Sort descending
        // this.newRequestImage.sort((a, b) => a.rbId - b.rbId);
      }, (error) => {
        this.errorMsg = 'There was some internal error! Please try again later!';
      })
  }

  manageRequest(rbId: number) {
    this.reimbursementHttpService.manageRequest(rbId).subscribe(
      (response) => {
        this.loadRequests();
      },
      (error) => {
      }
    )
  }

  denyRequest(rbId: number) {
    this.reimbursementHttpService.denyRequest(rbId).subscribe(
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

  toggleAdd() {
    if (this.flag) {
      this.flag = false;
    } else {
      this.flag = true;
    }
  }

  getAllUserRequest(userId: number) {
    this.reimbursementHttpService.getSpecificRequests(userId).subscribe(
      (response) => {
        this.allUserRequests = response;
      },
      (error) => {
      }
    )
  }

  goToEditComponent(rbId: any) {
    this.router.navigate(['reimbursement-edit'])
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

  loadRequests2() {
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

}