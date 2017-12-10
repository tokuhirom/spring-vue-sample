<template>
    <div>
        <router-link to="/product/add" class="btn btn-light">Create new product</router-link>
        <div>Products</div>
        <table class="table table-bordered">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>&nbsp;</th>
            </tr>
            <template v-for="product in products">
                <tr>
                    <td>{{product.id}}</td>
                    <td>{{product.name}}</td>
                    <td>
                        <router-link :to="{path:'/product/edit/'+product.id}" class="btn btn-light">Edit</router-link>
                    </td>
                    <td>
                        <router-link :to="{path:'/product/delete/'+product.id}" class="btn btn-danger">Delete</router-link>
                    </td>
                </tr>
            </template>
        </table>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        data() {
            return {
                products: []
            }
        },
        mounted() {
            axios.get('/api/product/')
                .then((response) => {
                    this.products = response.data.products;
                });
        }
    }
</script>

