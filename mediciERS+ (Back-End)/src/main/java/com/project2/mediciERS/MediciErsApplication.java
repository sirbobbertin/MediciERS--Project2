package com.project2.mediciERS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({"com.project2.mediciERS"})
//@EntityScan("com.project2.mediciERS")
//@EnableJpaRepositories("com.project2.mediciERS")
public class MediciErsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediciErsApplication.class, args);
	}

}
