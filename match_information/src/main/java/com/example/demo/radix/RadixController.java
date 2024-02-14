package com.example.demo.radix;

import com.example.demo.happyNumber.HappyNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping(path = "api/v1/radix")

public class RadixController {

	@Autowired
	public RadixController() {

	}

	@GetMapping
	public BigInteger getRadix(@RequestParam int radix) {
		return new BigInteger(String.valueOf(11), radix);
	}

}
