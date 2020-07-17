package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.data.domain.Pageable;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.comparator.PlatComparator;
import dev.config.JpaTestConfig;
import dev.dao.PlatDaoJpa;
import dev.entite.Ingredient;
import dev.entite.Plat;

@SpringJUnitConfig(classes = {JpaTestConfig.class})
@ActiveProfiles("jpa")
@Transactional
public class PlatRepositoryIntegrationTest {
	
	@Autowired private PlatRepository pR;
	
	@Test
	void testFindAll() {
		List<Plat> plats = pR.findAll();
		assertThat(plats).size().isEqualTo(6);
	}
	
	@Test
	void testFindAllSortAsc() {
		Sort sort = Sort.by("nom").ascending();
		List<Plat> plats = pR.findAll(sort);
		assertThat(plats).isSortedAccordingTo(new PlatComparator());
	}
	
	@Test
	void testFindAllSortDesc() {
		Sort sort = Sort.by("nom").descending();
		List<Plat> plats = pR.findAll(sort);
		assertThat(plats).isSortedAccordingTo(new PlatComparator().reversed());
	}
	
	@Test
	void testFindAllPageable() {
		Pageable pageable = PageRequest.of(0, 2, Sort.by("nom").ascending());
		Page<Plat> plats = pR.findAll(pageable);
		assertThat(plats).size().isEqualTo(2);
		assertThat(plats).extracting(Plat::getNom).containsExactly("Blanquette de veau", "Couscous");
	}
	
	@Test
	void testFindById() {
		Optional<Plat> plat = pR.findById(2);
		assertThat(plat).isNotEmpty();
		String nom = plat.get().getNom();
		assertThat(nom).contains("Moules-frites");
	}
	
	@Test
	void testGetOne() {
		Plat plat = pR.getOne(3);
		String nom = plat.getNom();
		assertThat(nom).contains("Couscous");
	}
	
	@Test
	void testCount() {
		Long nombre = pR.count();
		assertThat(nombre).isEqualTo(6);
	}
	
	@Test
	void testFindByPrixEnCentimesEuros() {
		List<Plat> plats = pR.findByPrixEnCentimesEuros(1300);
		assertThat(plats).size().isEqualTo(2);
	}
	
	@Test
	void testAvgPrix() {
		int moyenne = pR.avgPrix(2000);
		assertThat(moyenne).isEqualTo(2500);
	}
	
	@Test
	void testFindByNomWithIngredients() {
		List<Ingredient> ingredients = pR.findByNomWithIngredients("Moules-frites");
		assertThat(ingredients).size().isEqualTo(6);
	}
	
	@Test
	@Modifying
	void testSave() {
		Plat plat = new Plat("Tempura", 1200);
		pR.save(plat);
		List<Plat> plats = pR.findAll();
		assertThat(plats).size().isEqualTo(7);
		assertThat(plats).extracting(Plat::getNom).contains("Tempura");
		
	}
	
	@Test
	void testChangerNom() {
		pR.changerNom("Couscous", "Ramen");
		List<Plat> plats = pR.findAll();
		assertThat(plats).extracting(Plat::getNom).contains("Ramen");
	}
}


