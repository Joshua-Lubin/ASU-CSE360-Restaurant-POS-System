import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditCheckoutComponent } from './credit-checkout.component';

describe('CreditCheckoutComponent', () => {
  let component: CreditCheckoutComponent;
  let fixture: ComponentFixture<CreditCheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreditCheckoutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreditCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
