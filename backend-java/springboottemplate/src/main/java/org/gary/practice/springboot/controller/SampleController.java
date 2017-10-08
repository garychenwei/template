package org.gary.practice.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {
	@RequestMapping(value="hello",method= {RequestMethod.GET})
	public String hello() {
		return "hellow work";
	}
	
	@RequestMapping(value="session/add",method= {RequestMethod.GET})
	public String saveSession(HttpServletRequest request) {
		request.getSession().setAttribute("sample", "hello");
		return "saved";
	}
	
	@RequestMapping(value="session/get",method= {RequestMethod.GET})
	public String getSession(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("sample");
	}
}
