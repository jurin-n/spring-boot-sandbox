package com.example.openidconnectdemo;

public class Token {
	private String idToken;
	private String state;

	public String getIdToken() {
		return idToken;
	}

	public String getState() {
		return state;
	}

	public void setId_token(String id_token) {
		this.idToken = id_token;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Token [idToken=" + idToken + ", state=" + state + "]";
	}
}
