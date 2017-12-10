import Vue from "vue";
import App from './components/App.vue';
import VueRouter from 'vue-router';
import routes from './routes.js';
import axios from "axios";

Vue.use(VueRouter);

axios.interceptors.response.use(null, function(error) {
    console.log(error);
    window.alert(error.config.url + ": " + error + "\n\n" + JSON.stringify(error.response.data));
    return Promise.reject(error);
});

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
