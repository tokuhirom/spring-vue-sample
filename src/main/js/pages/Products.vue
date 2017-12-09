<template>
    <div>
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
            axios.get('/api/products')
                .then((response) => {
                    this.products = response.data.products;
                });
        }
    }
</script>

