import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputMb3Component } from './input-mb3.component';

describe('InputMb3Component', () => {
  let component: InputMb3Component;
  let fixture: ComponentFixture<InputMb3Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InputMb3Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InputMb3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
