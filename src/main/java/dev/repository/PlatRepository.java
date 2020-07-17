package dev.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entite.Ingredient;
import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {

	List<Plat> findByPrixEnCentimesEuros(Integer prixEnCentimesEuros);
	
	@Query("select avg(prixEnCentimesEuros) from Plat p where p.prixEnCentimesEuros > :prix")
	int avgPrix(@Param("prix") int prix);
	
	@Query("select i from Ingredient i join i.plats p where p.nom= ?1")
	List<Ingredient> findByNomWithIngredients(String nom);
	
	@Modifying
	@Query("update Plat p set p.nom=?2 where p.nom= ?1")
	void changerNom(String nomActuel, String nouveauNom);

	
}
