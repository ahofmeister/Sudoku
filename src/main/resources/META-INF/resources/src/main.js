import App from './App.vue'
import Vue from 'vue'
const moment = require('moment')
require('moment/locale/es')

import BootstrapVue from 'bootstrap-vue/dist/bootstrap-vue.esm';

// Import the styles directly. (Or you could add them via script tags.)
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

Vue.use(BootstrapVue);

Vue.use(require('vue-moment'), {
    moment
})
moment.locale('en', {
    week: {
        dow: 6
    }
});
new Vue({
  render: h => h(App)
}).$mount('#app')
