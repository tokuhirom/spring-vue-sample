var webpack = require("webpack");

module.exports = {
    entry: './src/main/js/app.js',
    output: {
        filename: 'bundle.js',
        path: __dirname + '/build/resources/main/static'
    },
    module: {
        loaders: [
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.js$/,
                use: 'babel-loader',
                exclude: /node_modules/
            }
        ]
    },
    plugins: [
    ]
}
;

