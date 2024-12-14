import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateNewsFormComponent } from './update-news-form.component';

describe('UpdateNewsFormComponent', () => {
  let component: UpdateNewsFormComponent;
  let fixture: ComponentFixture<UpdateNewsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateNewsFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateNewsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
