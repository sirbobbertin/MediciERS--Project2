import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { ManagerComponent } from './manager/manager.component'
import { UserService } from './user.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';


describe('UserService', () => {
  let service: UserService;
  let http: HttpClient;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        UserService,
      ]
    });
    service = TestBed.inject(UserService);
    http = TestBed.inject(HttpClient);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

});