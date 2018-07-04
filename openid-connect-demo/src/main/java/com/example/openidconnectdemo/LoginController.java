package com.example.openidconnectdemo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
	@PostMapping("/login")
	public void login(@ModelAttribute Token token){
		System.out.println(token.toString());
	}
}
