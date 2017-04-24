# 目的

サーバーサイドエンジニアが気軽に vue.js + spring-boot で SPA 開発できるようにしたい。

# セットアップ

まず、npm install で、package.json という npm の設定ファイルを生成します。

    npm install -y

開発時に必要なライブラリ・アプリケーションをインストールします。
`--save-dev` オプションは、インストール後に package.json に開発時依存項目として追記するためのオプションです。

    npm install --save-dev webpack babel-core babel-preset-es2015 vue-loader

vue.js, vue-router という、実行時に利用するライブラリをインストールします。
`--save` オプションは、インストール後に package.json に依存項目として追記するためのオプションです。

    npm install --save vue

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
