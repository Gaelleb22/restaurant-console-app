package dev.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import dev.entite.Plat;

@Repository
@Profile("platDoaJdbc")
public class PlatDaoJdbc implements IPlatDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public PlatDaoJdbc(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Plat> listerPlats() {
		
		RowMapper<Plat> mapper = (ResultSet rs, int rowNum)->{
			Plat p = new Plat();
			p.setId(rs.getInt("id"));
			p.setNom(rs.getString("nom"));
			p.setPrixEnCentimesEuros(rs.getInt("prix"));
			return p;
		};
		
		String sql = "SELECT * FROM plat";
		List<Plat> plats = this.jdbcTemplate.query(sql, mapper);
		return plats;
	}

	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		String sql = "INSERT INTO plat (nom, prix) VALUES(?, ?)";
		jdbcTemplate.update(sql, nomPlat, prixPlat);
		
	}
	
	

}
