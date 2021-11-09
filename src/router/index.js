import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeComponent from '../views/inicial/HomeComponent.vue'
import CadastroComponent from '../views/cadastro/Cadastro.vue'
import CadastroOrquidea from '../views/cadastro/CadastroOrquidea.vue'
import CadastroCupons from '../views/cadastro/AdminCadastroCupons.vue'
import LoginComponent from '../views/login/Login.vue'
import MinhaContaComponent from '../views/minha-conta/MinhaContaComponent.vue'
import EnderecoComponent from '../views/minha-conta/EnderecoComponent.vue'
import CadastroEnderecoComponent from '../views/cadastro/CadastroEndereco.vue'
import AlterarEndereco from '../views/minha-conta/AlterarEndereco.vue'
import Cartoes from '../views/minha-conta/Cartoes.vue'
import AlterarSenha from '../views/minha-conta/AlterarSenha'
import AlterarCartao from '../views/minha-conta/AlterarCartao.vue'
import CadastroCartao from '../views/cadastro/CadastroCartao.vue'
import DetalhesOrquideas from '../views/detalhes/Orquidea.vue'
import Carrinho from '../views/detalhes/Carrinho.vue'
import Pedidos from '../views/detalhes/Pedidos.vue'
import DetalhesPedidos from '../views/detalhes/DetalhesPedidos.vue'
import AdminCupons from '../views/detalhes/AdminCupons.vue'
import AdminPedidos from '../views/detalhes/AdminPedidos.vue'
import Cupom from '../views/detalhes/Cupom.vue'
import AdminDetalhesPedido from '../views/detalhes/AdminDetalhesPedido.vue'
import Mensagens from '../views/detalhes/Mensagens.vue'

Vue.use(VueRouter)

const routes = [
  { path: '', component: HomeComponent },
  { path: '/cadastro', component: CadastroComponent },
  { path: '/login', component: LoginComponent },
  { path: '/minha-conta', component: MinhaContaComponent },
  { path: '/minha-conta/endereco', component: EnderecoComponent },
  { path: '/minha-conta/alterar-endereco', component: AlterarEndereco },
  { path: '/minha-conta/cartoes', component: Cartoes },
  { path: '/minha-conta/alterar-cartao', component: AlterarCartao },
  { path: '/minha-conta/alterar-senha', component: AlterarSenha },
  { path: '/cadastro-endereco', component: CadastroEnderecoComponent },
  { path: '/cadastro-cartao', component: CadastroCartao },
  { path: '/cadastro-orquidea', component: CadastroOrquidea },
  { path: '/cadastro-cupons', component: CadastroCupons },
  { path: '/detalhes-orquidea/:id', component: DetalhesOrquideas, props: true },
  { path: '/detalhes-carrinho', component: Carrinho },
  { path: '/detalhes-pedido', component: DetalhesPedidos },
  { path: '/detalhes-cupom', component: Cupom },
  { path: '/pedidos', component: Pedidos },
  { path: '/mensagens', component: Mensagens },
  { path: '/admin-pedidos', component: AdminPedidos },
  { path: '/admin-detalhes-pedido', component: AdminDetalhesPedido },
  { path: '/admin-cupons', component: AdminCupons },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
