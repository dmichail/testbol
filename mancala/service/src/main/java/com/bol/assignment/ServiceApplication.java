package com.bol.assignment;

import com.bol.assignment.model.Player;
import com.bol.assignment.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(PlayerRepository playerRepository){
		return (args) -> {

				//Default players
				playerRepository.save(new Player("Damon"));
				playerRepository.save(new Player("Michael"));
				playerRepository.save(new Player("Jennifer"));
				playerRepository.save(new Player("Margot"));

		};
	}


}
