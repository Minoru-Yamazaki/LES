import { NgModule } from '@angular/core';
import { CadastroComponent } from './cadastro/cadastro.component';
import { SharedModule } from '../shared/shared.module';
import { CadastroEnderecoComponent } from './cadastro-endereco/cadastro-endereco.component';
import { InicialComponent } from './inicial/inicial.component';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { PagamentoComponent } from './pagamento/pagamento.component';
import { MinhaContaCadastroComponent } from './minha-conta/minha-conta-cadastro/minha-conta-cadastro.component';
import { MinhaContaEnderecoComponent } from './minha-conta/minha-conta-endereco/minha-conta-endereco.component';

@NgModule({
  declarations: [
    CadastroComponent,
    CadastroEnderecoComponent,
    InicialComponent,
    LoginComponent,
    PagamentoComponent,
    MinhaContaCadastroComponent,
    MinhaContaEnderecoComponent
  ],
  imports: [
    SharedModule,
    RouterModule
  ]
})
export class CoreModule { }
