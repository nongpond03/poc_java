package com.example.demo.paratheses;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.springframework.stereotype.Service;

@Service
public class ParathesesService {

	public ParathesesService() {
	}

	public boolean isMatch(String s) {
		if (s.length() % 2 != 0) {
			return false;
		}

		Stack<String> stack = new Stack<String>();
		Map<String, String> parentheses = new HashMap<String, String>();
		parentheses.put("(", ")");
		parentheses.put("{", "}");
		parentheses.put("[", "]");

		for (int x = 0; x < s.length() - 1; x++) {
			char c = s.charAt(x);
			String closer = parentheses.get(Character.toString(c));
			if (closer != null) {
				stack.push(closer);
				continue;
			}

			String pop = stack.pop();

			if (!Character.toString(c).equals(pop)) {
				return false;
			}
		}

		return true;
	}
}
