import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuContaComponent } from './menu-conta.component';

describe('MenuContaComponent', () => {
  let component: MenuContaComponent;
  let fixture: ComponentFixture<MenuContaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuContaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuContaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
