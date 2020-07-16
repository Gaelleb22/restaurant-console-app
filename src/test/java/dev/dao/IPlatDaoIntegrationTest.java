package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import dev.entite.Plat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class IPlatDaoIntegrationTest {
	
	@Autowired 
	private IPlatDao dao;
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Test
	void listerPlatsNonVide() {
		List<Plat> plats = dao.listerPlats();
		assertThat(plats).size().isEqualTo(6);
	}
	
	@Test
	void ajouterPlat() {
		dao.ajouterPlat("far breton", 1100);
		String sql = "SELECT prix FROM plat WHERE nom = 'far breton'";
		Integer prix = jdbctemplate.queryForObject(sql, Integer.class);
		assertThat(prix).isEqualTo(1100);
	}
}
