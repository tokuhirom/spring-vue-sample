import ListProduct from "./pages/product/ListProduct.vue";
import AddProduct from "./pages/product/AddProduct.vue";
import EditProduct from "./pages/product/EditProduct.vue";
import DeleteProduct from "./pages/product/DeleteProduct.vue";
import NotFound from "./pages/NotFound.vue";
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
    },
    {
        path: '/product/edit/:id',
        component: EditProduct
    },
    {
        path: '/product/delete/:id',
        component: DeleteProduct
    },
    {
        path: '*',
        component: NotFound
    }
]
