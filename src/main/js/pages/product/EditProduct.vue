<template>
    <div>
        <h1>Edit product: {{$route.params.id}}</h1>
        <form v-on:submit.prevent="send">
            <input v-model="name" placeholder="product name">
            <button class="btn btn-primary">Update</button>
        </form>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        data() {
            return {name: ''}
        },
        mounted() {
            axios.get("/api/product/" + this.$route.params.id)
                .then((response) => {
                    this.name = response.data.product.name;
                });
        },
        methods: {
            send() {
                axios.post('/api/product/' + this.$route.params.id, {
                    name: this.name
                })
                    .then((response) => {
                        this.$router.push("/product/");
                    });
            }
        }
    };
</script>
