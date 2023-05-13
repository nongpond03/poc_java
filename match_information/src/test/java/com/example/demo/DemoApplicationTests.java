package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.config.DatasourceConfig;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	DatasourceConfig datasourceConfig;

	@Test
	public void contextLoads() {
		datasourceConfig.setup();
	}

	public void whenCreatePersonCalledVerified() {
	}
}
