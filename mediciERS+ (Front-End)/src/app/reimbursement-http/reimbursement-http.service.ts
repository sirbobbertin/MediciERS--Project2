import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../users/auth.service';
import { Reimbursement } from './reimbursement.model';
import { Router } from '@angular/router';
import { RequestImageModel } from '../upload-files/upload-files/upload-file.model';

@Injectable({
  providedIn: 'root'
})
export class ReimbursementHttpService {

  baseUrl = "http://localhost:8080/api/reimbursements";
  baseUrl2 = "http://localhost:8080";

  constructor(private http: HttpClient,
    private auth: AuthService,
    private router: Router) {


  }

  // GET A REIMBURSEMENT
  getAReimbursement(rbId: number): Observable<Reimbursement> {
    return this.http.get<Reimbursement>(this.baseUrl + "/" + rbId);
  }

  // ALL PENDING (MANAGER)
  getAllPendingRequests(): Observable<RequestImageModel[]> {
    return this.http.get<RequestImageModel[]>(this.baseUrl + "/manager/view/pending");
  }

  // ALL RESOLVED (MANAGER)
  getAllResolvedRequests(): Observable<Reimbursement[]> {
    return this.http.get<Reimbursement[]>(this.baseUrl + "/manager/resolved");
  }

  // AlL SPECIFIC EMPLOYEE (MANAGER)
  getSpecificRequests(userId: number): Observable<Reimbursement[]> {
    return this.http.get<Reimbursement[]>(this.baseUrl + "/all/employee/" + userId);
  }

  // // PENDING (EMPLOYEE)
  // getPendingRequests(userId: number): Observable<Reimbursement[]> {
  //   return this.http.get<Reimbursement[]>(this.baseUrl + "/pending/employee/" + userId);
  // }

  // RESOLVED (EMPLOYEE)
  getResolvedRequests(userId: number): Observable<Reimbursement[]> {
    return this.http.get<Reimbursement[]>(this.baseUrl + "/resolved/employee/" + userId);
  }

  // DELETE REIMBURSEMENT
  deleteRequest(rbId: number): Observable<Reimbursement> {
    return this.http.delete<Reimbursement>(this.baseUrl + "/remove/" + rbId);
  }

  // ADD REIMBURSEMENT
  submitRequest(addReimbursement: Reimbursement): Observable<Reimbursement> {
    return this.http.post<Reimbursement>(this.baseUrl + "/add", addReimbursement);
  }

  // UPDATE REIMBURSEMENT
  updateReimbursement(updateReimbursement: Reimbursement): Observable<Reimbursement> {
    return this.http.put<Reimbursement>(this.baseUrl + "/update/" + updateReimbursement.rbId, updateReimbursement);
  }

  // APPROVE REIMBURSEMENT
  manageRequest(rbId: number): Observable<Reimbursement> {
    return this.http.put<Reimbursement>(this.baseUrl + "/approve/" + rbId, rbId);
  }

  // DENY REIMBURSEMENT
  denyRequest(rbId: number): Observable<Reimbursement> {
    return this.http.put<Reimbursement>(this.baseUrl + "/deny/" + rbId, rbId);
  }

  // GO TO EDIT COMPONENT
  goToEditComponent(rbId: any) {
    this.router.navigate(['reimbursement-edit', rbId])
  }

  getFilesByRb(rbId: number): Observable<any> {
    return this.http.get(`${this.baseUrl2}/files/reimbursement/`+ rbId,{responseType: "blob"});
  }

  upload(file: File, rbId: number): Observable<File> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    console.log(formData.get("file"));
    return this.http.post<File>(this.baseUrl2 + "/upload/" + rbId, formData);
  //   const req = new HttpRequest('POST', `${environment.baseUrl}/upload/21`, formData, {
  //     reportProgress: true,
  //     responseType: 'json'
  //   });
  //   return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/files`);
  }

  // PENDING (EMPLOYEE)
  getPendingRequests(userId: number): Observable<RequestImageModel[]> {
    return this.http.get<RequestImageModel[]>(this.baseUrl + "/pending/employee/" + userId);
  }

  // getFiles(): Observable<any> {
  //   return this.http.get(`${this.baseUrl}files/91`,{responseType: "blob"});
  // }



}
