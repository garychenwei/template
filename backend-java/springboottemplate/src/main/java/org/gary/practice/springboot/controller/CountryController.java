package org.gary.practice.springboot.controller;

import org.gary.practice.springboot.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController {
	
	@Autowired
	private CountryService countryService;

	@RequestMapping(value="",method= {RequestMethod.GET})
	public Object search() {
		return countryService.search();
	}
}
