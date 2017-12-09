import Vue from "vue";
import App from './components/App.vue';
import VueRouter from 'vue-router';
import routes from './routes.js';

Vue.use(VueRouter)

const router = new VueRouter({
    base: __dirname,
    routes: routes
});

document.addEventListener("DOMContentLoaded", function () {
    new Vue({
        router,
        render: h => h(App)
    }).$mount('#app');
});
