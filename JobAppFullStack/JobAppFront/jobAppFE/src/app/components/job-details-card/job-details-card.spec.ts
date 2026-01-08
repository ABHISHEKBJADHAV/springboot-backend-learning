import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobDetailsCard } from './job-details-card';

describe('JobDetailsCard', () => {
  let component: JobDetailsCard;
  let fixture: ComponentFixture<JobDetailsCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobDetailsCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JobDetailsCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
