import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentrequestsComponent } from './appointmentrequests.component';

describe('AppointmentrequestsComponent', () => {
  let component: AppointmentrequestsComponent;
  let fixture: ComponentFixture<AppointmentrequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppointmentrequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentrequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
