import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RequestImageModel } from './upload-files/upload-file.model';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  upload(file: File, rbId: number): Observable<File> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post<File>(this.baseUrl + "/upload/" + rbId, formData);
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

  getFilesByRb(rbId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/files/reimbursement/`+ rbId,{responseType: "blob"});
  }

}