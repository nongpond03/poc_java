package com.example.demo.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/command")

public class CommandController {
	@GetMapping
	public ResponseEntity execute() throws IOException, InterruptedException {
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
			builder.command("cmd.exe", "/c", "dir");
		} else {
			builder.command("sh", "-c", "ls");
		}
		builder.directory(new File(System.getProperty("user.home")));
		Process process = builder.start();

		int exitCode = process.waitFor();
		System.out.println("exitCode: " + exitCode);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
