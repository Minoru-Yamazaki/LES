import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroEnderecoComponent } from './core/cadastro-endereco/cadastro-endereco.component';
import { CadastroComponent } from './core/cadastro/cadastro.component';
import { CoreModule } from './core/core.module';
import { InicialComponent } from './core/inicial/inicial.component';
import { LoginComponent } from './core/login/login.component';
import { MinhaContaCadastroComponent } from './core/minha-conta/minha-conta-cadastro/minha-conta-cadastro.component';
import { MinhaContaEnderecoComponent } from './core/minha-conta/minha-conta-endereco/minha-conta-endereco.component';
import { PagamentoComponent } from './core/pagamento/pagamento.component';

const routes: Routes = [
  { path: '', component: InicialComponent },
  { path: 'home', component: InicialComponent },
  { path: 'login', component: LoginComponent },  
  { path: 'cadastro', component: CadastroComponent },
  { path: 'cadastro/endereco', component: CadastroEnderecoComponent },
  { path: 'pagamento', component: PagamentoComponent },
  { path: 'minha-conta', component: MinhaContaCadastroComponent },
  { path: 'minha-conta/cadastro', component: MinhaContaCadastroComponent },
  { path: 'minha-conta/endereco', component: MinhaContaEnderecoComponent },
  { path: '**', component: InicialComponent },  
];

@NgModule({
  imports: [
    CoreModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
