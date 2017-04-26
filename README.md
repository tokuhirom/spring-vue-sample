# 目的

サーバーサイドエンジニアが気軽に vue.js + spring-boot で SPA 開発するためにはどうしたらいいか、という話。

登場するコンポーネントは以下のやつ。

 * webpack(複数の JS をくっつけるやつ)
 * npm(js の package manager)
 * babel(ES2015 を昔ながらの JS に変換するのに使うやつ)
 * vue.js(簡単便利な js frameworkで、弊社社内でメインで利用されてるやつ)
 
ディレクトリ構造は以下のようにします

    src/main/js/app.js                    ← entry point
    build/resources/main/static/bundle.js ← 成果物

# セットアップ

まず、npm install で、package.json という npm の設定ファイルを生成します。

    npm install -y

開発時に必要なライブラリ・アプリケーションをインストールします。
`--save-dev` オプションは、インストール後に package.json に開発時依存項目として追記するためのオプションです。

    npm install --save-dev webpack babel-core babel-preset-es2015 vue-loader css-loader vue-style-loader vue-html-loader vue-template-compiler

vue.js, vue-router という、実行時に利用するライブラリをインストールします。
`--save` オプションは、インストール後に package.json に依存項目として追記するためのオプションです。

    npm install --save vue vuex

# babel の設定

babel の設定は .babelrc という設定ファイルにもかきます。

参考: https://babeljs.io/docs/usage/babelrc/

このプロジェクトでは以下のように設定しています。ES2015 のトランスパイルに利用しているだけです。

```
{
  "presets": [
    "es2015"
  ]
}
```

# webpack の設定

webpack は JS/CSS 等の静的コンテンツを依存も含めて1つのファイルにまとめてくれるライブラリです。babel 等の前処理をかますことも可能です(babel は transpiler framework で、主に ES2015 を古いブラウザでも実行できるようにするために利用されています)。

```javascript
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
    }
}
;
```

webpack は `./node_modules/webpack/bin/webpack.js` とすると、実行できます。
`./node_modules/webpack/bin/webpack.js -w` とすると、プロセスが常駐し、js ファイルを監視して、更新があったらリビルドされるようになります。

# gradle とのつなぎ込み

webpack を実行する task を設定します。簡単ですね。

```groovy
def webpackBin = "./node_modules/webpack/bin/webpack.js"

task installWebpack {
    if (!new File(webpackBin).exists()) {
        commandLine "npm", "install"
    }
}

task webpack(type: Exec, dependsOn: 'installWebpack') {
    executable webpackBin
}

task webpackWatch(type: Exec, dependsOn: 'installWebpack') {
    executable webpackBin
    args "-w"
}

build.dependsOn webpack
```

`./gradlew build` すると webpack が実行され、jar の中にビルド済みの js が梱包されます。

開発時には `./gradlew webpackWatch` とします。これにより、ソース・ファイルが変更されたタイミングで随時 build/ にファイルが生成されるようになります。

# vue-router を利用した開発

TBD

# SEE ALSO

 * http://shigekitakeguchi.github.io/2016/08/10/1.html
