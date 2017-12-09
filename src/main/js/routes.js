import ListProduct from "./pages/product/ListProduct.vue";
import AddProduct from "./pages/product/AddProduct.vue";
import Home from "./pages/Home.vue";

export default [
    {
        path: '/',
        component: Home
    },
    {
        path: '/product/',
        component: ListProduct
    },
    {
        path: '/product/add',
        component: AddProduct
    }
]
