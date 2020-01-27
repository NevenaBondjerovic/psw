import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkcalendarComponent } from './workcalendar.component';

describe('WorkcalendarComponent', () => {
  let component: WorkcalendarComponent;
  let fixture: ComponentFixture<WorkcalendarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkcalendarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkcalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
