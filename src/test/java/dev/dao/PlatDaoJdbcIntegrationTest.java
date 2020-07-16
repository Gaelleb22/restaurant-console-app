package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.DataSourceH2TestConfig;
import dev.config.JdbcTestConfig;
import dev.entite.Plat;

@SpringJUnitConfig(classes = {PlatDaoJdbc.class, JdbcTestConfig.class})
@ActiveProfiles({"platDoaJdbc"})
public class PlatDaoJdbcIntegrationTest {
	
	@Autowired 
	private PlatDaoJdbc platDaoJdbc;
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Test
	void listerPlatsNonVide() {
		List<Plat> plats = platDaoJdbc.listerPlats();
		assertThat(plats).size().isEqualTo(6);
	}
	
	@Test
	void ajouterPlat() {
		platDaoJdbc.ajouterPlat("far breton", 1100);
		String sql = "SELECT prix FROM plat WHERE nom = 'far breton'";
		Integer prix = jdbctemplate.queryForObject(sql, Integer.class);
		assertThat(prix).isEqualTo(1100);
	}

}
