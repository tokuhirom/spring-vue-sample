import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        products: []
    },
    getters: {
        products(state) {
            console.log(state);
            return state.products;
        }
    },
    mutations: {
        products(state, products) {
            state.products = products;
        }
    },
    actions: {
        getProducts(context) {
            axios.get('/api/products')
                .then(function (response) {
                    context.commit('products', response.data.products);
                });
        }
    }
});