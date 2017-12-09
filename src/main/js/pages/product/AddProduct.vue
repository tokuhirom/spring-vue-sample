<template>
    <div>
        <h1>Add new product</h1>
        <form v-on:submit.prevent="send">
            <input v-model="name" placeholder="product name">
            <button>Register new product</button>
        </form>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        data() {
            return { name: '' }
        },
        methods: {
            send() {
                axios.post('/api/product/add', {
                    name: this.name
                })
                    .then((response) => {
                        this.$router.push("/product/");
                    });
            }
        }
    };
</script>
