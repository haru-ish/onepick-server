# Web Server for One Pick 

## 概要
Androidアプリ「[One Pick](https://github.com/haru-ish/one-pick)」がWebサーバーを介してOpenAI APIと通信するためのWebアプリです。<br>
GooglePlayストアなどに「[One Pick](https://github.com/haru-ish/one-pick)」をリリースした際に、自身のAPIキーを保護する目的で本アプリを実装しました。

## 技術スタック
### 開発言語 / フレームワーク / ライブラリ etc
Java / Spring Boot / Lombok
### コード管理
GitHub

## ローカル環境での実行
### 事前準備
1. Android Studioをインストールする<br>
https://developer.android.com/studio/install?hl=ja

2. OpenAIとTMDBのAPIキーを取得する<br>
`OpenAI:` https://openai.com/<br>
`TMDB:` https://themoviedb.org

3. [One Pick](https://github.com/haru-ish/one-pick)のクローンを作成する（ブランチは`webserver`を選択）
```shell
$ git clone -b webserver https://github.com/haru-ish/one-pick.git

$ cd one-pick
```
4. `local.properties`ファイルを作成し、TMDBのAPIキーをファイル内に指定する。このファイルを`one-pick/app`と同じ階層に追加する

```:local.properties
// Android SDKの場所を指定（下記は例）
sdk.dir=/Users/UserName/Library/Android/sdk

// 自身のAPIキーを指定
tmdb_api_key=your-own-tmdb-apiKey
```
5. `app/src/main/java/com/example/onepick/data`の`ServerContainer.kt`を開き、`192.168.x.x`を自身のIPアドレスに変更する
```Kotlin:ServerContainer.kt
class DefaultServerContainer : ServerContainer {
     // 自身のIPアドレスを指定
    private val baseUrl = "http://192.168.x.x:8080/api/"
}    
```
 
6. Android Studioでプロジェクトを立ち上げ、エミュレーターか実機を選択し、`Run'app'`ボタンを押下してアプリを実行する

### 起動
1. リポジトリのクローンを作成する
```shell
$ git clone https://github.com/haru-ish/onepick-server.git
  
$ cd onepick-server
```
2. `src/main/resources` の `application.yml` に自身のOpenAI APIキーを指定する
```:application.yml
openai:
  model: gpt-3.5-turbo
  api:
    url: https://api.openai.com/v1/chat/completions
    // 自身のOpenAI APIキーを指定
    key: your-actual-openai-apiKey
```

3. 適切な統合開発環境（IDE）を選択し、プロジェクトを立ち上げる<br>
*Eclipse、IntelliJ IDEA、NetBeansなど

 4. `src/main/resources/java/com/example/onepickapi`の` OnePickApiApplication.java`を開き、`Run`ボタンを押下してそのクラスを実行する
