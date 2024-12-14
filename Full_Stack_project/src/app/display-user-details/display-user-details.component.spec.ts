import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayUserDetailsComponent } from './display-user-details.component';

describe('DisplayUserDetailsComponent', () => {
  let component: DisplayUserDetailsComponent;
  let fixture: ComponentFixture<DisplayUserDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DisplayUserDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayUserDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
