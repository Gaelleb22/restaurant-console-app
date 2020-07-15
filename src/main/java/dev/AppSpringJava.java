package dev;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import dev.ihm.Menu;
import dev.ihm.options.OptionAjouterPlat;
import dev.config.AppConfig;

public class AppSpringJava {

	public static void main(String[] args) {
		
		// Création du contexte Spring à partir d'une configuration Java
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.getEnvironment().setActiveProfiles("platServ2", "platDoaFich");
		
		context.register(AppConfig.class);
		context.refresh();
		// récupération du bean Menu
		Menu menu = context.getBean(Menu.class);
		menu.afficher();
		// fermeture du Scanner
		context.getBean(Scanner.class).close();
		// fermeture du contexte Spring
		context.close();

	}

}
