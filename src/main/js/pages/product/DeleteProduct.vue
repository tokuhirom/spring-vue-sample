<template>
    <div>
        <h1>Delete product: {{product.name}}</h1>
        <form v-on:submit.prevent="send">
            <button class="btn btn-danger">Delete</button>
        </form>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        data() {
            return {product: {name: null}}
        },
        mounted() {
            axios.get("/api/product/" + this.$route.params.id)
                .then((response) => {
                    this.product = response.data.product;
                });
        },
        methods: {
            send() {
                axios.delete('/api/product/' + this.$route.params.id, {
                    name: this.name
                })
                    .then((response) => {
                        this.$router.push("/product/");
                    });
            }
        }
    };
</script>
