package com.rrortegav.olimpiadas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OlimpiadasApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlimpiadasApplication.class, args);
	}

}
