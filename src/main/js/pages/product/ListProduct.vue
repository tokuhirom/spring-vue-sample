<template>
    <div>
        <router-link to="/product/add" class="btn btn-default">Create new product</router-link>
        <div>Products</div>
        <ul>
            <template v-for="product in products">
                <li>Name: {{product.name}}</li>
            </template>
        </ul>
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

