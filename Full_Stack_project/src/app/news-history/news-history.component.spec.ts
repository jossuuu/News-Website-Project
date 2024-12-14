import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsHistoryComponent } from './news-history.component';

describe('NewsHistoryComponent', () => {
  let component: NewsHistoryComponent;
  let fixture: ComponentFixture<NewsHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NewsHistoryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
