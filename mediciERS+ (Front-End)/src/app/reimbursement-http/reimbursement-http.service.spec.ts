import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { ReimbursementHttpService } from './reimbursement-http.service';


class MessageServiceSub{
  getTitle(){
    return "Welcome to Unit Test";
  }
}

describe('ReimbursementHttpService', () => {
  let service: ReimbursementHttpService;


  beforeEach(() => {
    TestBed.configureTestingModule({
     imports: [HttpClientTestingModule,RouterTestingModule]
    });
    service = TestBed.inject(ReimbursementHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('get A Reimbursement should be read', () => {
    expect(service.getAReimbursement(2)).toBeTruthy();
  });

});
