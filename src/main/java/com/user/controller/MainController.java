package com.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class MainController {
// /user/check/status
	@GetMapping("check/status")
	public String getStatus() {
		return "I Am Still Alive";
	}
}
