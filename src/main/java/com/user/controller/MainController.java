package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user")
public class MainController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private RestTemplate restTemplate;
	
// /user/check/status
	@GetMapping("check/status")
	public String getStatus() {
		return "I Am Still Alive "+environment.getProperty("local.server.port");
	}
	
	@GetMapping("check/interCommute")
	@CircuitBreaker(fallbackMethod = "sendReport", name = "UserService")
	public String getOtherService() {
		
		String url="http://localhost:8800/account/message";
		String result=restTemplate.getForObject(url, String.class);
		return result;
	}
	
	public String sendReport() {
		return "service is currently down ,please try after some time";
	}
}


//hit http://localhost:8000/userservice/user/check/status 
//api on postman then it will return different ports so thats how load balancer works
