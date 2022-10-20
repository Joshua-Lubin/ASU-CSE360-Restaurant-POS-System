import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsuLogoComponent } from './asu-logo.component';

describe('AsuLogoComponent', () => {
  let component: AsuLogoComponent;
  let fixture: ComponentFixture<AsuLogoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AsuLogoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AsuLogoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
