## ビルド&起動
```
mvn clean package && java -jar target/openid-connect-demo-0.0.1-SNAPSHOT.jar
```

## Azure AD連携
```
TENANT_ID=xx
CLIENT_ID=xxx
echo "GET https://login.microsoftonline.com/${TENANT_ID}/oauth2/authorize?client_id=${CLIENT_ID}&response_type=id_token&redirect_uri=http%3a%2f%2flocalhost%3a8080%2flogin&response_mode=form_post&scope=openid&state=1111&nonce=7362CAEA-9CA5-4B43-9BA3-34D7C303EBA7"
```

## 参考
https://docs.microsoft.com/ja-jp/azure/active-directory/develop/active-directory-protocols-openid-connect-code
https://qiita.com/TakahikoKawasaki/items/4ee9b55db9f7ef352b47