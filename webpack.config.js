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
            },
            {
                test: /\.css$/,
                use: [
                    {loader: "style-loader"},
                    {loader: "css-loader"},
                ],
            },
            {
                // bootstrap に含まれる font を data url に変換する。
                test: /\.(svg|ttf|woff2|woff|eot)$/,
                loader: 'url-loader'
            }]
    },
    plugins: []
}
;

