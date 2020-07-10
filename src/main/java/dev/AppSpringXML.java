package dev;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppSpringXML {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-config-memoire.xml");
		Scanner scanner = context.getBean(Scanner.class);
		context.close();

	}

}
