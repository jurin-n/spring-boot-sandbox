### テスト用DB
#### 起動コマンド
```
docker run --name postgres-dev -e POSTGRES_PASSWORD=dev123 -p 5432:5432 -d postgres 
```
#### テスト用DBユーザーなど
```
ユーザー：postgres
パスワード：dev123
データーベース：postgres
```


### 実行
```
mvn clean package && java -jar target/csv-processing-0.0.1-SNAPSHOT.jar
```