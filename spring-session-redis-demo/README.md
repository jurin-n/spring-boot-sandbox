### redisをdockerで起動
``` 
ls -a1 redis-data || mkdir redis-data
docker run -p 6379:6379 -v $PWD/redis-data:/data --name redis -d redis redis-server --appendonly yes
```

### redis-cliでredisに接続
```
docker run -it --link redis:redis --rm redis redis-cli -h redis -p 6379
```

###  
```
mvn clean package && mvn spring-boot:run 
```


