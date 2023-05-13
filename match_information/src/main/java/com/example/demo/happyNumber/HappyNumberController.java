package com.example.demo.happyNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/happyNumber")

public class HappyNumberController {

	private HappyNumberService service;

	@Autowired
	public HappyNumberController(HappyNumberService service) {
		this.service = service;

	}

	@GetMapping
	public boolean isHappyNumber(@RequestParam int number) {
		return service.isHappyNumber(number);
	}

}
