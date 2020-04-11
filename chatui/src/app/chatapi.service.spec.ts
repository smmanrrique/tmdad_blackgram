import { TestBed, inject } from '@angular/core/testing';

import { ChatapiService } from './chatapi.service';

describe('ChatapiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ChatapiService]
    });
  });

  it('should be created', inject([ChatapiService], (service: ChatapiService) => {
    expect(service).toBeTruthy();
  }));
});
