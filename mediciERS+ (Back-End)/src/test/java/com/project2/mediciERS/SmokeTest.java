package com.project2.mediciERS;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.mediciERS.controller.Controller;

@SpringBootTest
public class SmokeTest {
	@Autowired
	private Controller controller;
	
	@Test
	public void contexLoads() throws Exception {
		assertThat(controller) .isNotNull();
	}

}
