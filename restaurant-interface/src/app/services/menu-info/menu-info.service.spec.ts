import { TestBed } from '@angular/core/testing';

import { MenuInfoService } from './menu-info.service';

describe('MenuInfoService', () => {
  let service: MenuInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MenuInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
