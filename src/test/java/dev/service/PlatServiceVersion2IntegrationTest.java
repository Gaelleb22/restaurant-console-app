package dev.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.AppConfig;
import dev.exception.PlatException;

@SpringJUnitConfig(AppConfig.class)
public class PlatServiceVersion2IntegrationTest {
	
	@Autowired private PlatServiceVersion2 service2;
	
	
	@Test
	void ajouterPlatValide() {
		service2.ajouterPlat("dessert", 1500);
	}
	
	@Test
	void ajouterPlatPrixInvalide() {
		assertThatThrownBy(() -> service2.ajouterPlat("dessert", 20))
		.isInstanceOf(PlatException.class)
		.hasMessage("le prix d'un plat doit être supérieur à 10 €");
	}

}
