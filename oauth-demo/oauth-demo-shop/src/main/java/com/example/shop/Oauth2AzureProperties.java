package com.example.shop;

import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "oauth2.azure")
public class Oauth2AzureProperties {
	private URI authorizeEndpoint;
	private String clientId;
	private String clientSecret;
	private URI jwkEndpoint;
	private Integer jwkConnectTimeout;
	private Integer jwkReadTimeout;
	private Integer jwkSizeTimit;

	public URI getAuthorizeEndpoint() {
		return authorizeEndpoint;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public URI getJwkEndpoint() {
		return jwkEndpoint;
	}

	public Integer getJwkConnectTimeout() {
		return jwkConnectTimeout;
	}

	public Integer getJwkReadTimeout() {
		return jwkReadTimeout;
	}

	public Integer getJwkSizeTimit() {
		return jwkSizeTimit;
	}

	public void setAuthorizeEndpoint(URI authorizeEndpoint) {
		this.authorizeEndpoint = authorizeEndpoint;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setJwkEndpoint(URI jwkEndpoint) {
		this.jwkEndpoint = jwkEndpoint;
	}

	public void setJwkConnectTimeout(Integer jwkConnectTimeout) {
		this.jwkConnectTimeout = jwkConnectTimeout;
	}

	public void setJwkReadTimeout(Integer jwkReadTimeout) {
		this.jwkReadTimeout = jwkReadTimeout;
	}

	public void setJwkSizeTimit(Integer jwkSizeTimit) {
		this.jwkSizeTimit = jwkSizeTimit;
	}

}
