package com.jurin_n.demo;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
	private final AccessToken accessToken;

	public DemoController(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	@PostMapping("/login")
	public String login(@RequestParam("client_id") Optional<String> clientId,
			@RequestParam("type") Optional<String> type) {
		if (accessToken.getToken() == null) {
			accessToken.setToken(clientId.orElse("anonymous"));
			accessToken.setType(type.orElse("TYPEXX"));
			accessToken.setCreatedAt(new Date());
			return "stored " + accessToken.getToken() + " type=" + accessToken.getType() + " in session" + " at "
					+ accessToken.getCreatedAt();
		}
		return "already login. token is " + accessToken.getToken() + ".";
	}

	@GetMapping
	public String get() {
		if (accessToken.getToken() == null) {
			return "don't login.";
		}
		return "already login. token is " + accessToken.getToken() + ".";
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		String token = accessToken.getToken();
		session.invalidate();
		return "logout. token was " + token;
	}
}
