package com.example.shop.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class Token implements Serializable {
	private static final long serialVersionUID = 8048097948251750715L;
	private String idToken;
	private Date createdAt;

	public String getIdToken() {
		return idToken;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
