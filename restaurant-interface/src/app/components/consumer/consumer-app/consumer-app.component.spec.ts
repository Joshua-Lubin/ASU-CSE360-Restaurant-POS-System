import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumerAppComponent } from './consumer-app.component';

describe('ConsumerAppComponent', () => {
  let component: ConsumerAppComponent;
  let fixture: ComponentFixture<ConsumerAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsumerAppComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsumerAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
