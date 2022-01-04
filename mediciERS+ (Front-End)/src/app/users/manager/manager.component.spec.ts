import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from '../auth.service';
import { User } from '../login/user.model';
import { UserService } from '../user.service';

import { ManagerComponent } from './manager.component';

describe('ManagerComponent', () => {
  let component: ManagerComponent;
  let fixture: ComponentFixture<ManagerComponent>;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let userService: UserService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,RouterTestingModule],
      declarations: [ ManagerComponent ],
      providers: [UserService, AuthService]
    })
    .compileComponents();
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    userService = TestBed.inject(UserService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('#loadUser', () =>{
    let expectedUsers: User[];
    beforeEach(() =>{

      expectedUsers = [
      {
        userId: 2, userPassword: '123456', userFirstName: 'Savid', userLastName: 'Mark',
        userAddress: 'Detriut', userContact: '555-666-4444', userType: 'employee',userRemoved: false
      },
      {userId: 3, userPassword: '123456', userFirstName: 'Eavid', userLastName: 'Bark',
      userAddress: 'Detriut', userContact: '555-666-4444', userType: 'employee',userRemoved: false}  
    ] as User[];
  });

      //Test case 1
      it('should return expected users by calling once', () => {
        userService.getAllUsers().subscribe(
          emps => expect(emps).toEqual(expectedUsers, 'should return expected users'),
          fail
        );
        const req = httpTestingController.match(userService.baseUrl+'/employees');
        expect(req[1].request.method).toEqual('GET');
        req[1].flush(expectedUsers); //Return expectedEmps
      });

          //Test case 2
    it('should be OK returning no employee', () => {
      userService.getAllUsers().subscribe(
        emps => expect(emps.length).toEqual(0, 'should have empty user array'),
        fail
      );
      const req = httpTestingController.match(userService.baseUrl+'/employees');
      expect(req[0].request.method).toEqual('GET');
      req[0].flush([]); //Return empty data
    });

        //Test case 3
        it('should return expected employees when called multiple times', () => {
          userService.getAllUsers().subscribe();
          userService.getAllUsers().subscribe(
            emps => expect(emps).toEqual(expectedUsers, 'should return expected employees'),
            fail
          );
          const requests = httpTestingController.match(userService.baseUrl+'/employees');
          expect(requests.length).toEqual(3, 'calls to getAllEmployees()');
          requests[0].flush([]); //Return Empty body for first call
          requests[1].flush(expectedUsers); //Return expectedEmps in second call
        });

  });
  
});