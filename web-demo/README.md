
### 開発用DB
```
docker run --name postgres-dev -e POSTGRES_PASSWORD=dev123 -p 5432:5432 -d postgres 
```

```
ユーザー：postgres
パスワード：dev123
データーベース：postgres
```

### ビルド＆起動
```

mvn clean install -Dmaven.test.skip=true && java -jar target/web-demo-0.0.1-SNAPSHOT.jar
```


### DBスキーマ
```
-- Table Definition ----------------------------------------------

CREATE TABLE top_info (
    id integer PRIMARY KEY,
    title character varying(512),
    datetime timestamp without time zone
);

-- Indices -------------------------------------------------------

CREATE UNIQUE INDEX top_info_pkey ON top_info(id int4_ops);
```

### テストデータ
```
insert into top_info(id,title,datetime) values(1, 'タイトル０１', '2019-04-04 20:00:00');
insert into top_info(id,title,datetime) values(2, 'タイトル０２', '2019-04-05 20:00:00');
insert into top_info(id,title,datetime) values(3, 'タイトル０３', '2019-04-06 20:00:00');
insert into top_info(id,title,datetime) values(4, 'タイトル０４', '2019-04-07 20:00:00');
insert into top_info(id,title,datetime) values(5, '鷗外', '2019-04-08 20:00:00');
```
