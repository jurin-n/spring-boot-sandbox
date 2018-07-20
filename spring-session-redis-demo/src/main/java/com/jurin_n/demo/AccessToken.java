package com.jurin_n.demo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class AccessToken implements Serializable {

	private static final long serialVersionUID = 8048097948251750715L;
	private String token;
	private Date createdAt;

	public String getToken() {
		return token;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
