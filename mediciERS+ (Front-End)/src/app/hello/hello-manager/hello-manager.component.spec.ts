import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HelloManagerComponent } from './hello-manager.component';

describe('HelloManagerComponent', () => {
  let component: HelloManagerComponent;
  let fixture: ComponentFixture<HelloManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HelloManagerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HelloManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
