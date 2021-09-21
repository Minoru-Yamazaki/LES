import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './assets/css/bootstrap.min.css'; //awesome.min
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'
import VueResource from 'vue-resource';
import VueCookie from 'vue-cookie'

Vue.use(VueCookie)
Vue.use(VueResource);
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
