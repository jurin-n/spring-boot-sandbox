# コマンドなど
### redisをdockerで起動
``` 
ls -a1 redis-data || mkdir redis-data
docker run -p 6379:6379 -v $PWD/redis-data:/data --name redis -d redis redis-server --appendonly yes
```

### redis-cliでredisに接続
```
docker run -it --link redis:redis --rm redis redis-cli -h redis -p 6379
```

### ビルド&環境変数設定&起動
```
mvn clean package && \
export AZURE_AD_TENANT_ID=xxx && \
export AZURE_AD_CLIENT_ID=xxxx && \
export AZURE_AD_CLIENT_SECRET=xxxxx && \
java -jar target/oauth-demo-shop-0.0.1-SNAPSHOT.jar
```



# 参考ドキュメント
```
OAuth / OpenID Connectを中心とするAPIセキュリティについて
https://www.slideshare.net/tkudo/oauth-oidc-api-security-yuzawaws

OpenID Connect 全フロー解説
https://qiita.com/TakahikoKawasaki/items/4ee9b55db9f7ef352b47

OAuth 2.0の代表的な利用パターンを仕様から理解しよう
https://www.buildinsider.net/enterprise/openid/oauth20

azure-active-directory-spring-boot-starter/Implementation Summary
https://github.com/Microsoft/azure-spring-boot/tree/master/azure-spring-boot-starters/azure-active-directory-spring-boot-starter#implementation-summary

On-Behalf-Of フローでの委任ユーザー ID を使ったサービス間の呼び出し
https://docs.microsoft.com/ja-jp/azure/active-directory/develop/active-directory-protocols-oauth-on-behalf-of
```

