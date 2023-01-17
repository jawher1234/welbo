import { TestBed } from '@angular/core/testing';

import { navbarService } from './navbar.service';

describe('navbarService', () => {
  let service: navbarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(navbarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
