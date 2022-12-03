import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatusPromptComponent } from './status-prompt.component';

describe('StatusPromptComponent', () => {
  let component: StatusPromptComponent;
  let fixture: ComponentFixture<StatusPromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StatusPromptComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StatusPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
