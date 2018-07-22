package com.example.bank;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {
	@GetMapping("/account")
	public Account getAccount(){
		return new Account("account001");
	}
}
