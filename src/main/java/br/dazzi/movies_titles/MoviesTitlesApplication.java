package br.dazzi.movies_titles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MoviesTitlesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesTitlesApplication.class, args);
	}

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}


}
