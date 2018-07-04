package com.example.openidconnectdemo;

public class Token {
	private String id_token;
	private String state;

	public String getId_token() {
		return id_token;
	}

	public String getState() {
		return state;
	}

	public void setId_token(String id_token) {
		this.id_token = id_token;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Token [id_token=" + id_token + ", state=" + state + "]";
	}
}
