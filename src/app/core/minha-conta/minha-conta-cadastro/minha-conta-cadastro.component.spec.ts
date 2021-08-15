import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MinhaContaCadastroComponent } from './minha-conta-cadastro.component';

describe('MinhaContaCadastroComponent', () => {
  let component: MinhaContaCadastroComponent;
  let fixture: ComponentFixture<MinhaContaCadastroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MinhaContaCadastroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MinhaContaCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
