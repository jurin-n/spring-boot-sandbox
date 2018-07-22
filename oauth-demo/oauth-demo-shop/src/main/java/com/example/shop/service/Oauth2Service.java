package com.example.shop.service;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.example.shop.Oauth2AzureProperties;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;

@Service
public class Oauth2Service {
    private Oauth2AzureProperties oauth2AzureProperties;

    public Oauth2Service(Oauth2AzureProperties oauth2AzureProperties) {
        this.oauth2AzureProperties = oauth2AzureProperties;
    }

    public String verifyJwt(String idToken) {
        // id token検証し、問題なければレスポンス。問題あれば例外スロー。
        try {
            // parse jwt
            SignedJWT signedJWT = SignedJWT.parse(idToken);

            // HTTP connect timeout in milliseconds
            int connectTimeout = oauth2AzureProperties.getJwkConnectTimeout();// 1000;
            // HTTP read timeout in milliseconds
            int readTimeout = oauth2AzureProperties.getJwkReadTimeout();// 1000;
            // JWK set size limit, in bytes
            int sizeLimit = oauth2AzureProperties.getJwkSizeTimit();// 10000;

            // Load JWK set from URL(Azure AD)
            URI uri = oauth2AzureProperties.getJwkEndpoint();
            URL url = uri.toURL();
            JWKSet publicKeys = JWKSet.load(url, connectTimeout, readTimeout, sizeLimit);
            JWK jwk = publicKeys.getKeyByKeyId(signedJWT.getHeader().getKeyID());
            RSAKey rsaKey = RSAKey.parse(jwk.toJSONString());

            /* verify jwt */
            JWSVerifier verifier = new RSASSAVerifier(rsaKey.toRSAPublicKey());
            if (signedJWT.verify(verifier)) {
                // TODO 戻り値何にすべきか迷い中。
                // String aud = signedJWT.getPayload().toJSONObject().getAsString("aud");
                // String email = signedJWT.getPayload().toJSONObject().getAsString("email");
                return signedJWT.getParsedString();
            }
            throw new RuntimeException();
        } catch (ParseException | IOException | JOSEException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
