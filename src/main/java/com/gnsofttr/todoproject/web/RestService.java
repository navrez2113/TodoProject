package com.gnsofttr.todoproject.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestService {

	@RequestMapping("/hello")
	public String hello() {
		return "hello world";
	}

}
