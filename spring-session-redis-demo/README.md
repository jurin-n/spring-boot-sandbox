### redisをdockerで起動
``` 
ls -a1 redis-data || mkdir redis-data
docker run -p 6379:6379 -v $PWD/redis-data:/data --name redis -d redis redis-server --appendonly yes
```

### redis-cliでredisに接続
```
docker run -it --link redis:redis --rm redis redis-cli -h redis -p 6379
```

### ビルド&起動
```
mvn clean package && mvn spring-boot:run 
```

## リクエスト方法(curl使う場合)
### セッション作成
```
curl -D - -d "client_id=ID001&type=TYPE01" \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -X POST http://localhost:8080/demo/login
```

### セッションクッキーつけてリクエスト
```
curl -D - \
     --cookie "SESSION=N2QyMDJkMGUtNmQ2MS00Njg5LTlhNWItZDkzNjhjMDNkYzkx" \
     -X POST http://localhost:8080/demo/login
```
```
curl -D - \
     --cookie "SESSION=N2QyMDJkMGUtNmQ2MS00Njg5LTlhNWItZDkzNjhjMDNkYzkx" \
     -X GET http://localhost:8080/demo
```

### セッション格納情報取得


### セッション破棄


