# 目的

サーバーサイドエンジニアが気軽に vue.js + spring-boot で SPA 開発するためにはどうしたらいいか、という話。

登場するコンポーネントは以下のものたち。

 * spring-boot(Java の web application framework)
 * webpack(複数の JS をくっつけたりします)
 * npm(js の package manager)
 * babel(ES2015 を昔ながらの JS に変換するのに使います)
 * vue.js(簡単便利な js frameworkで、弊社社内でメインで利用されてるやつ)

ディレクトリ構造は以下のようにします。

    src/main/java/                        ← java code
    src/main/js/                          ← JS code
    build/resources/main/static/bundle.js ← 成果物

最終的には  webpack の成果物が含まれる uber jar(fat jar ともいう)が `./gradlew build` で生成されるようにします。

# セットアップ

node.js を最新版にしてください。

webpack をインストールします。

    npm install -g webpack

まず、npm install で、package.json という npm の設定ファイルを生成します。

    npm install -y

開発時に必要なライブラリ・アプリケーションをインストールします。
`--save-dev` オプションは、インストール後に package.json に開発時依存項目として追記するためのオプションです。

    npm install --save-dev webpack babel-core babel-preset-es2015 babel-loader \
        vue-loader vue-style-loader vue-html-loader vue-template-compiler \
        file-loader style-loader url-loader css-loader \
        extract-text-webpack-plugin webpack

vue.js, vue-router という、実行時に利用するライブラリをインストールします。
`--save` オプションは、インストール後に package.json に依存項目として追記するためのオプションです。

    npm install --save vue bootstrap vue-router axios

# webpack の設定

webpack は JS/CSS 等の静的コンテンツを依存も含めて1つのファイルにまとめてくれるアプリケーションです。
babel 等の前処理も webpack で呼び出します(babel は transpiler framework で、主に ES2015 を古いブラウザでも実行できるようにするために利用されています)。

ExtractTextPlugin は、CSS ファイルを別ファイルとして書き出すために利用しています。

```javascript
const webpack = require("webpack");
const ExtractTextPlugin = require("extract-text-webpack-plugin");

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
        use: ExtractTextPlugin.extract({
          fallback: "style-loader",
          use: "css-loader"
        })
      },
      // bootstrap に含まれる font 等を data url に変換する。
      {test: /\.svg$/, loader: 'url-loader?mimetype=image/svg+xml'},
      {test: /\.woff$/, loader: 'url-loader?mimetype=application/font-woff'},
      {test: /\.woff2$/, loader: 'url-loader?mimetype=application/font-woff'},
      {test: /\.eot$/, loader: 'url-loader?mimetype=application/font-woff'},
      {test: /\.ttf$/, loader: 'url-loader?mimetype=application/font-woff'}
    ]
  },
  plugins: [
    new ExtractTextPlugin("styles.css"),
  ]
}
;
```

webpack は `webpack` コマンドを実行するだけで利用できます。
開発時には `webpack -w` とすると、プロセスが常駐し、ファイルの更新を監視して、更新があったらリビルドされるようになります。

# gradle とのつなぎ込み

webpack を実行する gradle task を設定します。
`./gradlew build` した時に、uber jar の中に webpack の成果物を埋めるような設定を行います。

```groovy
buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath 'com.moowork.gradle:gradle-node-plugin:0.12'
    }
}

apply plugin: 'java'
apply plugin: "com.moowork.node"

node {
    version = '6.6.0'
    npmVersion = '3.10.7'
    download = true
}

task webpack(type: NodeTask, dependsOn: 'npmInstall') {
    def osName = System.getProperty("os.name").toLowerCase();
    if (osName.contains("windows")) {
        script = project.file('node_modules/webpack/bin/webpack.js')
    } else {
        script = project.file('node_modules/.bin/webpack')
    }
}

processResources.dependsOn 'webpack'

clean.delete << file('node_modules')

```

`./gradlew build` すると webpack が実行され、jar の中にビルド済みの js が梱包されます。

# `npm run dev` コマンドの設定

`webpack -w` を IntelliJ IDEA から呼び出しやすいように npm のサブコマンドを定義しておきます。
package.json に以下のように記述します。

```
{
  "name": "spring-vue-sample",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "dev": "webpack -w"
  }, // 以下略
}
```

こうすることにより、`npm run dev` で `webpack -w` を実行できます。

# SEE ALSO

 * http://shigekitakeguchi.github.io/2016/08/10/1.html
 * https://webpack.js.org/plugins/extract-text-webpack-plugin/#usage-example-with-css
