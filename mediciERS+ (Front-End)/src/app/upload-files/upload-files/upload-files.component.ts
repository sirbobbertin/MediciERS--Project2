import { Component, OnInit } from '@angular/core';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { AuthService } from 'src/app/users/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/users/login/user.model';
import { Reimbursement } from 'src/app/reimbursement-http/reimbursement.model';
import { ReimbursementHttpService } from 'src/app/reimbursement-http/reimbursement-http.service';
import { Observable } from 'rxjs';
import { RequestImageModel } from './upload-file.model';
import { DomSanitizer } from '@angular/platform-browser';
import { UploadFileService } from '../upload-file.service';

@Component({
  selector: 'app-upload-files',
  templateUrl: './upload-files.component.html',
  styleUrls: ['./upload-files.component.scss']
})
export class UploadFilesComponent implements OnInit {

  flag: boolean = false;

  PendingRequests: Reimbursement[] = [];
  newRequestImage: RequestImageModel[] = [];

  selectedFiles?: FileList;
  currentFile?: File;
  message = '';
  errorMsg = '';


  newRequest = {
    rbId: 0,
    userId: 0,
    rbDate: "",
    rbAmount: 0,
    rbStatus: "",
    rbResolved: false,
    rbRemoved: false,
    fileURL: ""
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

  loadRequests() {
    this.reimbursementHttpService.getPendingRequests(this.userInfo.userId).subscribe(
      (response) => {
        this.newRequestImage = response;
        this.newRequestImage.forEach((reimbursement, index) => {
          const mediaType = 'application/image';
          this.reimbursementHttpService.getFilesByRb(reimbursement.rbId).subscribe(value => {
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

  toggleAdd() {
    if (this.flag) {
      this.flag = false;
    } else {
      this.flag = true;
    }
  }

  addReimbursement() {
    this.newRequest.userId = this.userInfo.userId;
    this.reimbursementHttpService.submitRequest(this.newRequest).subscribe(
      (response) => {
        this.newRequest.rbId = response.rbId;
        this.upload();
        this.loadRequests();
      },
      (error) => {
      }
    )
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
        // this.router.navigate(['reimbursement-edit']);
      },
      (error) => {
      }
    );
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
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



}
