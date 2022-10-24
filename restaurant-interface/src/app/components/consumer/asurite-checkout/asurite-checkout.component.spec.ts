import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsuriteCheckoutComponent } from './asurite-checkout.component';

describe('AsuriteCheckoutComponent', () => {
  let component: AsuriteCheckoutComponent;
  let fixture: ComponentFixture<AsuriteCheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AsuriteCheckoutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AsuriteCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
