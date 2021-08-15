import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MinhaContaEnderecoComponent } from './minha-conta-endereco.component';

describe('MinhaContaEnderecoComponent', () => {
  let component: MinhaContaEnderecoComponent;
  let fixture: ComponentFixture<MinhaContaEnderecoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MinhaContaEnderecoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MinhaContaEnderecoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
