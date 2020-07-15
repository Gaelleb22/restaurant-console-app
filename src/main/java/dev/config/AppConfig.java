package dev.config;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import dev.ihm.options.IOptionMenu;
import dev.ihm.options.OptionAjouterPlat;

@Configuration
@ComponentScan("dev")
@PropertySource("app.properties")
@Import(OptionAjouterPlat.class)
public class AppConfig {
	
	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}

}
