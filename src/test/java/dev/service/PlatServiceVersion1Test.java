package dev.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.dao.IPlatDao;
import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;
import dev.exception.PlatException;

public class PlatServiceVersion1Test {
	
	private IPlatDao mockedDao; 
	private PlatServiceVersion1 service;
	private Plat plat;
	
	@BeforeEach
	void setUp() {
		mockedDao = Mockito.mock(IPlatDao.class);
		this.service = new PlatServiceVersion1(mockedDao);
	}
	
	@Test
	void ajouterPlatNomInvalide() {
		PlatException thrown = Assertions.assertThrows(PlatException.class, () -> service.ajouterPlat("pi", 1500));
		assertTrue(thrown.getMessage().contains("un plat doit avoir un nom de plus de 3 caractères"));
	}
	
	@Test
	void ajouterPlatPrixInvalide() {
		
		//exemple de correction
		assertThatThrownBy(() -> service.ajouterPlat("gâteau", 300))
			.isInstanceOf(PlatException.class)
			.hasMessage("le prix d'un plat doit être supérieur à 5 €");
		
		
		//PlatException thrown = Assertions.assertThrows(PlatException.class, () -> service.ajouterPlat("pizza", 15));
		//assertTrue(thrown.getMessage().contains("le prix d'un plat doit être supérieur à 5 €"));
	}
	
	@Test
	void ajouterPlatValide() {
		service.ajouterPlat("pizza", 1500);
		verify(mockedDao).ajouterPlat("pizza", 1500);
		
	}

}
