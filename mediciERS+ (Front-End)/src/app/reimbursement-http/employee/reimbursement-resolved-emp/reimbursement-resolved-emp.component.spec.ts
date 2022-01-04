import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ReimbursementHttpService } from '../../reimbursement-http.service';
import { Reimbursement } from '../../reimbursement.model';

import { ReimbursementResolvedEmpComponent } from './reimbursement-resolved-emp.component';

describe('ReimbursementResolvedEmpComponent', () => {
  let component: ReimbursementResolvedEmpComponent;
  let fixture: ComponentFixture<ReimbursementResolvedEmpComponent>;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let reimbursementService: ReimbursementHttpService;


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      declarations: [ReimbursementResolvedEmpComponent],
      providers: [ReimbursementHttpService]
    })
      .compileComponents();
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    reimbursementService = TestBed.inject(ReimbursementHttpService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReimbursementResolvedEmpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('#loadReimbursements', () => {
    let expectedReimbursement: Reimbursement[];
    beforeEach(() => {

      expectedReimbursement = [
        {
          rbId: 2, userId: 3, rbDate: '12/12/2021', rbAmount: 200,
          rbStatus: 'APPROVED', rbResolved: true, rbRemoved: false
        },
        {
          rbId: 2, userId: 3, rbDate: '12/12/2021', rbAmount: 200,
          rbStatus: 'APPROVED', rbResolved: true, rbRemoved: false
        }
      ] as Reimbursement[];
    });

    //Test case 1
    it('should return expected pending reimbursement by calling once', () => {
      reimbursementService.getAllResolvedRequests().subscribe(
        emps => expect(emps).toEqual(expectedReimbursement, 'should return expected resolved reimbursements'),
        fail
      );
      const req = httpTestingController.match(reimbursementService.baseUrl + '/manager/resolved');
      expect(req[0].request.method).toEqual('GET');
      req[0].flush(expectedReimbursement); //Return expectedReimbursement
    });

    //Test case 2
    it('should be OK returning no reimbursement', () => {
      reimbursementService.getAllResolvedRequests().subscribe(
        emps => expect(emps.length).toEqual(0, 'should have empty reimbursement array'),
        fail
      );
      const req = httpTestingController.match(reimbursementService.baseUrl + '/manager/resolved');
      expect(req[0].request.method).toEqual('GET');
      req[0].flush([]); //Return empty data
    });

    //Test case 3
    it('should return expected employees when called multiple times', () => {
      reimbursementService.getAllResolvedRequests().subscribe();
      reimbursementService.getAllResolvedRequests().subscribe(
        emps => expect(emps).toEqual(expectedReimbursement, 'should return expected reimbursements'),
        fail
      );
      const requests = httpTestingController.match(reimbursementService.baseUrl + '/manager/resolved');
      expect(requests.length).toEqual(2, 'calls to getAllEmployees()');
      requests[0].flush([]); //Return Empty body for first call
      requests[1].flush(expectedReimbursement); //Return expectedReimbursement in second call
    });

  });

});