package dev.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

class PlatDaoMemoireTest {
	private PlatDaoMemoire platDaoMemoire;

	@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}

	@Test
	void listerPlatsVideALInitialisation() {
		List<Plat> resultat = platDaoMemoire.listerPlats();
		assertThat(resultat).isEmpty();
	}

	@Test
	void ajouterPlatCasPassants() {

		platDaoMemoire.ajouterPlat("galette", 1200);
		List<Plat> resultat = platDaoMemoire.listerPlats();
		assertThat(resultat).extracting(Plat::getNom).containsExactly("galette");
		assertThat(resultat).extracting(Plat::getPrixEnCentimesEuros).containsExactly(1200);
	}
}
