package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.config.JpaTestConfig;
import dev.entite.Plat;

@SpringJUnitConfig(classes = {PlatDaoJpa.class, JpaTestConfig.class})
@ActiveProfiles("jpa")
public class PlatDaoJpaIntegrationTest extends IPlatDaoIntegrationTest {

}
