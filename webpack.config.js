const webpack = require("webpack");
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const FileManagerPlugin = require('filemanager-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin')

module.exports = {
    mode: 'production', // https://webpack.js.org/configuration/mode/
    entry: './src/main/js/app.js',
    output: {
        filename: 'js/bundle.js',
        path: __dirname + '/build/resources/main/static'
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.js$/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                },
                exclude: /node_modules/
            },
            {
                test: /\.css$/i,
                use: [MiniCssExtractPlugin.loader, 'css-loader'],
            },
            // bootstrap に含まれる font 等を data url に変換する。
            {test: /\.svg$/, use: [ {loader: 'url-loader', options: { mimetype: 'image/svg+xml' }} ]},
            {test: /\.woff$/, use: [ {loader: 'url-loader', options: { mimetype: 'application/font-woff' }} ]},
            {test: /\.woff2$/, use: [ {loader: 'url-loader', options: { mimetype: 'application/font-woff' }} ]},
            {test: /\.eot$/, use: [ {loader: 'url-loader', options: { mimetype: 'application/font-woff' }} ]},
            {test: /\.ttf$/, use: [ {loader: 'url-loader', options: { mimetype: 'application/font-woff' }} ]}
        ]
    },
    plugins: [
        new VueLoaderPlugin(),
        new MiniCssExtractPlugin(),
        // IntelliJ IDEA uses out/production/resources/ as a classpath.
        new FileManagerPlugin({
            onEnd: {
                copy: [
                    {
                        source: __dirname + '/build/resources/main/static/js/bundle.js',
                        destination: __dirname + '/out/production/resources/static/js/bundle.js'
                    },
                    {
                        source: __dirname + '/build/resources/main/static/main.css',
                        destination: __dirname + '/out/production/resources/static/main.css'
                    }
                ]
            }
        })
    ]
}
;

