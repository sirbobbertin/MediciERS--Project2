import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReimbursementSubmitComponent } from './reimbursement-submit.component';

describe('ReimbursementSubmitComponent', () => {
  let component: ReimbursementSubmitComponent;
  let fixture: ComponentFixture<ReimbursementSubmitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReimbursementSubmitComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReimbursementSubmitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
