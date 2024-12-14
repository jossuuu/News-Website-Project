import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayLikesAndDislikesComponent } from './display-likes-and-dislikes.component';

describe('DisplayLikesAndDislikesComponent', () => {
  let component: DisplayLikesAndDislikesComponent;
  let fixture: ComponentFixture<DisplayLikesAndDislikesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DisplayLikesAndDislikesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayLikesAndDislikesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
