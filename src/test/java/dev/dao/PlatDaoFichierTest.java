package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;

@SpringJUnitConfig(classes = {PlatDaoFichier.class})
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoFichierTest {
	
	@Autowired private PlatDaoFichier platDaoFichier;
	
	@Test
	void ajouterPlatSauvegarde() {
		platDaoFichier.ajouterPlat("pizza", 1200);
		List<Plat> resultat = platDaoFichier.listerPlats();
		assertThat(resultat).extracting(Plat::getNom).containsExactly("pizza");
		assertThat(resultat).extracting(Plat::getPrixEnCentimesEuros).containsExactly(1200);
		assertThat(resultat).hasSize(1);
	}
	
	@Test
	void ajouterPlatSauvegarde2() {
		platDaoFichier.ajouterPlat("galette", 1200);
		List<Plat> resultat = platDaoFichier.listerPlats();
		assertThat(resultat).extracting(Plat::getNom).containsExactly("galette");
		assertThat(resultat).extracting(Plat::getPrixEnCentimesEuros).containsExactly(1200);
		assertThat(resultat).hasSize(1);
	}

}
