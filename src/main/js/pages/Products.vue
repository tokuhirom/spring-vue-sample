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
    import {mapGetters} from 'vuex'

    export default {
        computed: mapGetters(['products'])
    }
</script>
