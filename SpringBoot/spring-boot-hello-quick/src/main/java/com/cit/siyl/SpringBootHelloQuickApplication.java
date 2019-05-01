package com.cit.siyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class SpringBootHelloQuickApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloQuickApplication.class, args);
	}

}
