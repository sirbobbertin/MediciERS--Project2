import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HelloEmployeeComponent } from './hello-employee.component';

describe('HelloEmployeeComponent', () => {
  let component: HelloEmployeeComponent;
  let fixture: ComponentFixture<HelloEmployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HelloEmployeeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HelloEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
