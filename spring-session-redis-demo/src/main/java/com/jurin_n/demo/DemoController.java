package com.jurin_n.demo;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/login")
	public String login(@RequestParam Optional<String> name) {
		if (accessToken.getToken() == null) {
			accessToken.setToken(name.orElse("anonymous"));
			accessToken.setCreatedAt(new Date());
		}
		return accessToken.getToken() + " at " + accessToken.getCreatedAt();
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "";
	}
}
