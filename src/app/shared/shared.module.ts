import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputMb3Component } from './campos/input-mb3/input-mb3.component';
import { MenuContaComponent } from './menu/menu-conta/menu-conta.component';
import { RouterLink, RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    InputMb3Component,
    MenuContaComponent,

  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    InputMb3Component,
    MenuContaComponent,
  ]
})
export class SharedModule { }
